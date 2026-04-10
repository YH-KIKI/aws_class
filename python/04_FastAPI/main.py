from fastapi import FastAPI, UploadFile, Form
from contextlib import asynccontextmanager
from sentence_transformers import SentenceTransformer
import uvicorn
import mnist_learning as ml
import text_mining as tm
import movie_learning as mo
import numpy as np
import pandas as pd
import cv2
import fashion as fs
import chatbot as chat


# 모델들을 관리하는 전역 변수
ml_models = {}

@asynccontextmanager
async def lifespan(app:FastAPI):
	# 서버 시작시 무거운 모델을 미리 로드
	print("INFO: \t 모델 로딩 시작...")
	ml_models["ret_model"] = SentenceTransformer('jhgan/ko-sroberta-multitask')
	ml_models["ret_emb_data"] = chat.load_emb_data(ml_models["ret_model"], 'model/cached_emb_data.npy')

	print("INFO: \t 모델 로딩 완료...")
	yield

app = FastAPI(lifespan=lifespan)

@app.get('/')
async def index():
	return {"msg" : "Hello FastAPI"}

@app.post('/image')
async def image(file:UploadFile): 
	# file을 바이너리데이터로 읽어옴
	contents = await file.read()
	
  # 바이너를 numpy 배열로 변환
	np_arr = np.frombuffer(contents, np.uint8)
		
  # 배열을 이미지로 디코딩
	img = cv2.imdecode(np_arr, cv2.IMREAD_GRAYSCALE)

	print(f'URL : /image')
  # 예측
	res = ml.predict_from_upload_file(img, 28, 28)

	return {"msg" : res}

@app.get('/movies')
async def movies():
	return mo.get_movies()

@app.post('/movies/recommend')
async def movies_recommend(title:str=Form(...), type:str=Form(...)):
	recommender = mo.MovieRecommender()
	if type == 'content':
		recommender.load_model('model/movie_model_content.pkl')
	elif type == 'etc':
		recommender.load_model('model/movie_model_etc.pkl')
	elif type == 'director':
		recommender.load_model('model/movie_model_director.pkl')
	list = recommender.get_recommendations_movies(type, title)
	# 영화 제목 리스트를 딕셔너리로 변환해서 전송하기 위해
	# 제목 리스트를 데이터프레임으로 변환
	df = pd.DataFrame({'title':list})
	# 데이터 프레임을 제목만 딕셔너리로 변환
	return df[['title']].to_dict(orient='records')
	

@app.post('/text')
async def text(msg:str = Form(...)): 
	res = tm.predict(msg)
	return {"msg" : '긍정' if res else '부정'}

@app.post('/fashion')
async def fashion(file:UploadFile=Form(...)):
	# file을 바이너리데이터로 읽어옴
	contents = await file.read()
	# #예측
	res = fs.predict_from_upload_file(contents)
	print(f'URL : /fashion')
	return {"msg" : res}



@app.post('/chatbot')
async def chatbot(msg:str=Form(...)):
	msg, _ = chat.get_chatbot_response(
		msg,
		ml_models["ret_model"],
		ml_models["ret_emb_data"],)
	return {"msg" : msg}

if __name__ == '__main__':
	# reload=True 사용시 주의 사항.
	# 자동으로 변경 사항을 적용하면 시간이 오래 걸리는 경우 안 쓰는 것이 좋음
	uvicorn.run('main:app', port=8000, reload=True)

