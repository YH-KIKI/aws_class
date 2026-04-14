from sentence_transformers import SentenceTransformer
from contextlib import asynccontextmanager
from fastapi import FastAPI, Form
from pydantic import BaseModel
from typing import Optional
import learning as lr


models = {}

@asynccontextmanager
async def lifespan(app:FastAPI):
	# 서버 시작시 무거운 모델을 미리 로드
	print("INFO: \t 모델 로딩 시작...")
	models["ret_model"] = SentenceTransformer('jhgan/ko-sroberta-multitask')
	models["ret_emb_data"] = lr.load_emb_data(models["ret_model"], 'cached_emb_data.npy')

	print("INFO: \t 모델 로딩 완료...")
	yield


app = FastAPI(lifespan=lifespan)


class Test(BaseModel):
	msg : str

class ChatResponse(BaseModel):
	answer : str

@app.get("/")
async def index():
	lr.test()
	return {"message": "Hello FastAPI"}

@app.post("/test")
async def testPost(msg=Form(...)):
	print(msg)
	return {"msg" : "FastAPI"}

@app.post("/chat")
async def chatPost(test : Test):
	chat_response = ChatResponse(answer = "")
	chat_response.answer, _ = lr.get_chatbot_response(
		test.msg,
		models["ret_model"],
		models["ret_emb_data"],
		'ChatbotData.csv')
    
	# BaseModel이 객체를 json형태의 문자열로 반환
	return chat_response


if __name__ == "__main__":
	import uvicorn
	uvicorn.run("main:app", port=8000, reload=False)