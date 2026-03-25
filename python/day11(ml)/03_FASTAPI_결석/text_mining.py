from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.linear_model import LogisticRegression
import joblib as jl
import pandas as pd 
from konlpy.tag import Okt

# 문장에서 명사, 동사, 형용사면 원형을 추출해서 문장을 만들어 반환
def text_preprocessing(text):
	okt = Okt()
	result = okt.pos(text, stem=True)
	words = [word for word, pos in result if pos in  ['Noun', 'Verb', 'Adjective']]
	return " ".join(words)

def train_and_save_model(dataset_x, dataset_y):

	# 형태소 분리
	dataset_pp_x = dataset_x.apply(text_preprocessing)
	#벡터화 
	vectorizer = TfidfVectorizer()
	# 독립, 종속 분리
	X = vectorizer.fit_transform(dataset_pp_x)
	y = dataset_y
	# 학습
	model = LogisticRegression()
	model.fit(X, y)
	# 모델과 벡터라이저를 저장
	model_data = {
		'vectorizer' : vectorizer, 
		'model' : model
	}
	jl.dump(model_data, 'model.pkl')

def predict(text):
	# 모델과 벡터라이저를 불러옴
	mode_data = jl.load('model.pkl')
	model , vectorizer = mode_data['model'], mode_data['vectorizer']

	# 형태소가 처리된 문자열로 변환
	cleaned_text = text_preprocessing(text)

	# 문장을 벡터화 
	vector_text = vectorizer.transform([cleaned_text])

	# 예측
	predict = model.predict(vector_text)
	# 예측 결과를 반환(0 또는 1)
	return predict[0]

if __name__ == '__main__':
	# print(text_preprocessing("오늘은 날이 너무 너무 좋습니다."))
	# 테스트 할 때 sample.csv를 인식하게 하기 위해 cd day11(ml) 명령어 입력 후 실행
	df = pd.read_csv(r'sample.csv')
	train_and_save_model(df['sentence'], df['label'])
	print(predict('오늘은 날이 너무 너무 화창합니다.'))
	print(predict('날씨가 화창한데 자격증 시험에 떨어졌습니다.'))
	print(predict('상사한테 혼났습니다.'))
	