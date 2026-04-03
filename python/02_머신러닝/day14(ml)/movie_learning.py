import pandas as pd
import numpy as np
import joblib as jl
from ast import literal_eval
from sklearn.metrics.pairwise import cosine_similarity
from sklearn.feature_extraction.text import CountVectorizer
from sklearn.feature_extraction.text import TfidfVectorizer

def get_recommendations(title, df, cosine_sim):

  indices = pd.Series(df.index, index=df['title']).drop_duplicates()
  idx = indices[title]
  sim_scores = list(enumerate(cosine_sim[idx]))
  sim_scores = sorted(sim_scores, key=lambda x : x[1], reverse=True)
  sim_scores = sim_scores[1:11]
  movie_indices = [i[0] for i in sim_scores] 
  return df['title'].iloc[movie_indices]

def get_recommendations_movies(type:str, title:str):
	# 내용 기반 추천
	if type == 'content':
		cosine_sim, df = load_cosine_sim('cosin_sim_content.pkl')
		if df is None or cosine_sim is None:
			df = load_csv_to_df('data/tmdb_5000_credits.csv', 'data/tmdb_5000_movies.csv')
			cosine_sim = get_cosine_sim_content(df)
			save_cosine_sim(cosine_sim, df, 'cosin_sim_content.pkl')
		return get_recommendations(title, df, cosine_sim)

	# 감독, 장르, 배우, 키워드 추천
	elif type == 'etc':
		cosine_sim, df = load_cosine_sim('cosin_sim_etc.pkl')
		if df is None or cosine_sim is None:
			df = load_csv_to_df('data/tmdb_5000_credits.csv', 'data/tmdb_5000_movies.csv')
			cosine_sim = get_cosine_sim_etc(df)
			save_cosine_sim(cosine_sim, df, 'cosin_sim_etc.pkl')
		return get_recommendations(title, df, cosine_sim)
	else:
		return []

def load_csv_to_df(file_credits='tmdb_5000_credits.csv', file_movies='tmdb_5000_movies.csv'):
  df1 = pd.read_csv(file_credits)
  df2 = pd.read_csv(file_movies)
  df1.columns = ['id', 'title', 'cast', 'crew']
  df = df2.merge(df1[['id', 'cast', 'crew']], on = 'id')
  return df

# df가 주어졌을 때 내용 기반으로 학습된 코사인 유사도를 반환하는 함수
def get_cosine_sim_content(df):

  # 결측치 처리
  df['overview'] = df['overview'].fillna('')

  # 벡터화 
  tfidf = TfidfVectorizer(stop_words='english')
  tfidf_matrix = tfidf.fit_transform(df['overview'])
     
  # 코사인 유사도 계산
  cosine_sim = cosine_similarity(tfidf_matrix, tfidf_matrix)

  return cosine_sim

# 감독, 배우, 장르, 키워드 기반 코사인 유사도를 반환하는  함수
def get_cosine_sim_etc(df):

  def get_list(x):
    if isinstance(x, list):
      names = [i['name'] for i in x]
      if len(names) > 3:
        names = names[:3] #0번지부터 3번지 전까지 
      return names
    return []
  
  def clean_data(x):
    if isinstance(x, list):
      return [str.lower(i.replace(' ', '')) for i in x]
    elif isinstance(x, str):
      return str.lower(x.replace(' ', ''))
    else:
      return ''

  def get_director(x):
    for crew in x: 
      if crew['job'] == 'Director':
        return crew['name']
    return np.nan
  
  def create_soup(x):
    return f'{' '.join(x['keywords'])} {' '.join(x['cast'])} {' '.join(x['genres'])} {x['director']}'
  
  features = ['genres', 'keywords', 'cast', 'crew']
  # 문자열로된 값을 리스트로 변환
  for feature in features:
    df[feature] = df[feature].apply(literal_eval)
  
  df['director'] = df['crew'].apply(get_director)

  features = ['genres', 'keywords', 'cast']
  for feature in features:
    df[feature] = df[feature].apply(get_list)

  features = ['genres', 'keywords', 'cast', 'director']

  for feature in features:
    df[feature] = df[feature].apply(clean_data)

  df['soup'] = df.apply(create_soup, axis=1)

  count = CountVectorizer()
  count_matrix = count.fit_transform(df['soup'])

  return cosine_similarity(count_matrix, count_matrix)

# cosine 유사도를 피클(pkl)로 저장
def save_cosine_sim(cosine_sim, df, file_name):
  data = {
		'cosine_sim' : cosine_sim, 
    'df' : df
	}
  jl.dump(data, file_name)

def load_cosine_sim(file_name):
  try:
    data = jl.load(file_name)
    return data['cosine_sim'], data['df']
  except:
    return None, None


if __name__ == '__main__':
  # df = load_csv_to_df('data/tmdb_5000_credits.csv', 'data/tmdb_5000_movies.csv')
  # cosine_sim = get_cosine_sim_content(df)
  # save_cosine_sim(cosine_sim, df, 'cosine_sim_content.pkl')
  cosine_sim, df = load_cosine_sim('cosine_sim_content.pkl')
  print(get_recommendations_movies('content', 'Avatar'))

  # cosine_sim = get_cosine_sim_etc(df)
  # # save_cosine_sim(cosine_sim, 'cosine_sim_etc.pkl')
  # # cosine_sim, df = load_cosine_sim('cosine_sim_etc.pkl')
  # print(get_recommendations('Avatar', df, cosine_sim))
  # print(get_recommendations_movies('etc', 'Avatar'))
  pass