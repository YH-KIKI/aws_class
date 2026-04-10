import pandas as pd
import numpy as np
import joblib as jl
from ast import literal_eval
from sklearn.metrics.pairwise import cosine_similarity
from sklearn.feature_extraction.text import CountVectorizer
from sklearn.feature_extraction.text import TfidfVectorizer

class MovieRecommender:

  def __init__(self):
    self.df = None
    self.cosine_sim = None

  def load_data(self, file_credits, file_movies):
    df1 = pd.read_csv(file_credits)
    df2 = pd.read_csv(file_movies)
    df1.columns = ['id', 'title', 'cast', 'crew']
    self.df = df2.merge(df1[['id', 'cast', 'crew']], on = 'id')
    return self.df
  
  def calculate_content_cosine_sim(self):
    self.df['overview'] = self.df['overview'].fillna('')

    # 벡터화 
    tfidf = TfidfVectorizer(stop_words='english')
    tfidf_matrix = tfidf.fit_transform(self.df['overview'])
      
    # 코사인 유사도 계산
    self.cosine_sim = cosine_similarity(tfidf_matrix, tfidf_matrix)
    return self.cosine_sim
    
  def calculate_etc_cosine_sim(self):
    self.preprocessing_ect()

    count = CountVectorizer()
    count_matrix = count.fit_transform(self.df['soup'])

    self.cosine_sim = cosine_similarity(count_matrix, count_matrix)
    return self.cosine_sim
  
  def calculate_director_cosine_sim(self):
    self.preprocessing_director()
    
    # 결측치 처리
    self.df['director'] = self.df['director'].fillna('')

    # 벡터화(matrix생성)
    count = CountVectorizer()
    count_matrix = count.fit_transform(self.df['director'])

    # 코사인 유사도 계산
    self.cosine_sim = cosine_similarity(count_matrix, count_matrix)
    return self.cosine_sim 
  
  def preprocessing_director(self):
    features = ['crew']
    # 문자열로된 값을 리스트로 변환
    for feature in features:
      self.df[feature] = self.df[feature].apply(literal_eval)
    
    self.df['director'] = self.df['crew'].apply(self._get_director)

    features = ['director']
    for feature in features:
      self.df[feature] = self.df[feature].apply(self._clean_data)

    self.df['soup'] = self.df.apply(self._create_soup, axis=1)
    return self.df
  

  def preprocessing_ect(self):
    features = ['genres', 'keywords', 'cast', 'crew']
    # 문자열로된 값을 리스트로 변환
    for feature in features:
      self.df[feature] = self.df[feature].apply(literal_eval)
    
    self.df['director'] = self.df['crew'].apply(self._get_director)

    features = ['genres', 'keywords', 'cast']
    for feature in features:
      self.df[feature] = self.df[feature].apply(self._get_list)

    features = ['genres', 'keywords', 'cast', 'director']

    for feature in features:
      self.df[feature] = self.df[feature].apply(self._clean_data)

    self.df['soup'] = self.df.apply(self._create_soup, axis=1)
    return self.df


  
  def save_model(self, file_name):
    data = {
      'cosine_sim' : self.cosine_sim,
      'df' : self.df
    }
    jl.dump(data, file_name)

  def load_model(self, file_name):
    try:
      data = jl.load(file_name)
      self.cosine_sim, self.df = data['cosine_sim'], data['df']
    except:
      self.cosine_sim, self.df = None, None

  def get_recommendations(self, title):
    indices = pd.Series(self.df.index, index=self.df['title']).drop_duplicates()
    idx = indices[title]
    sim_scores = list(enumerate(self.cosine_sim[idx]))
    sim_scores = sorted(sim_scores, key=lambda x : x[1], reverse=True)
    sim_scores = sim_scores[1:11]
    movie_indices = [i[0] for i in sim_scores] 
    return self.df['title'].iloc[movie_indices]
  
  def get_recommendations_movies(self, type:str, title:str):
    if self.df is None:
      try:
        self.load_data('csv/tmdb_5000_credits.csv', 'csv/tmdb_5000_movies.csv')
      except:
        return []

    if type == 'content':
      if self.cosine_sim is None:
        self.calculate_content_cosine_sim()
        self.save_model('model/movie_model_content.pkl')
      return self.get_recommendations(title)
    # 감독, 장르, 배우, 키워드 추천
    elif type == 'etc':
      if self.cosine_sim is None:
        self.calculate_etc_cosine_sim()
        self.save_model('model/movie_model_etc.pkl')
      return self.get_recommendations(title)
    elif type == 'director':
      if self.cosine_sim is None:
        self.calculate_director_cosine_sim()
        self.save_model('model/movie_model_director.pkl')
      return self.get_recommendations(title)
    else:
      return []

  
  def _get_list(self, x):
    if isinstance(x, list):
      names = [i['name'] for i in x]
      if len(names) > 3:
        names = names[:3] #0번지부터 3번지 전까지 
      return names
    return []
  def _clean_data(self, x):
    if isinstance(x, list):
      return [str.lower(i.replace(' ', '')) for i in x]
    elif isinstance(x, str):
      return str.lower(x.replace(' ', ''))
    else:
      return ''
  def _get_director(self, x):
    for crew in x: 
      if crew['job'] == 'Director':
        return crew['name']
    return np.nan
  def _create_soup(self, x):
    return f'{' '.join(x['keywords'])} {' '.join(x['cast'])} {' '.join(x['genres'])} {x['director']}'
  

def get_movies():
  df = pd.read_csv('csv/tmdb_5000_credits.csv')
  df = df[['title']].dropna() # 제목 결측치처리
  return df[['title']].to_dict(orient='records')

if __name__ == '__main__':
  recommender = MovieRecommender()
  # 피클 만드는 방법
  # 1~3을 주석 해제, 나머지 주석. 실행하면 pkl파일 생성
  # 1. 
  # recommender.load_data('csv/tmdb_5000_credits.csv', 'csv/tmdb_5000_movies.csv')
  # 2.
  # recommender.calculate_content_cosine_sim()
  # 3.
  # recommender.save_model('model/movie_model_content.pkl')

  # python movie_learning.py
  # recommender.load_model('model/movie_model_content.pkl')
  # print(recommender.get_recommendations_movies('content', 'Avatar'))
  # print(recommender.df)

  # recommender = MovieRecommender()
  # # recommender.load_data('csv/tmdb_5000_credits.csv', 'csv/tmdb_5000_movies.csv')
  # # recommender.calculate_etc_cosine_sim()
  # # recommender.save_model('model/movie_model_etc.pkl')
  # recommender.load_model('model/movie_model_etc.pkl')
  # print(recommender.get_recommendations_movies('etc', 'Avatar'))
  # print(recommender.df)

  recommender = MovieRecommender()
  # recommender.load_data('csv/tmdb_5000_credits.csv', 'csv/tmdb_5000_movies.csv')
  # recommender.calculate_director_cosine_sim()
  # recommender.save_model('model/movie_model_director.pkl')
  recommender.load_model('model/movie_model_director.pkl')
  print(recommender.get_recommendations_movies('director', 'Avatar'))
  # print(recommender.df)
  pass