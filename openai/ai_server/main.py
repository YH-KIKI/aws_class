from google import genai
from google.genai import types
from fastapi import FastAPI, Query, Form
from pydantic import BaseModel
import uvicorn
import os

app = FastAPI()

# gemini-2.5-flash-lite
# 환경변수에서 키값 가져오는걸로 변경
GOOGLE_API_KEY = os.getenv('GOOGLE_API_KEY')
GOOGLE_MODEL_NAME = "gemini-2.5-flash-lite"
client = genai.Client(api_key=GOOGLE_API_KEY)


@app.get("/ask")
async def ask_gemini(prompt: str):
	response = client.models.generate_content(
    model= GOOGLE_MODEL_NAME,
    contents=types.Part.from_text(text=prompt),
	)
	return {
		"message" : response.text}	# 리액트 Ask.jsx의 20번줄 setResult랑 값맞춰야함 


@app.get("/translate")
async def translate(
	text:str = Query(..., description='번역할 문장'),
	style:str =  Query("formal", description='말투: formal(격식), casual(반말), business(비즈니스)')
):

	# 좋은 번역을 위하여 번역 문장을 좋은 프롬프트로 변환
	# I am a boy => 나는 소년이다
	# f : 문자열 안에 변수를 쉽게 넣을 때 사용
	# """ : 문자열이 여러줄이 가능하게 해줌
	prompt = f"""
	너는 세계 최고의 다국어 번역가야. 아래의 규칙을 지켜서 번역해줘.
	1. 대상 문장 : {text}
	2. 요청 말투 : {style} 말투로 번역해줘.
	3. 도착어 : 한국어면 영어로, 영어이면 한국어로 자동 감지해서 번역해줘.
	4. 결과물 : 번역된 문장 외에 다른 설명은 생략해
	"""
	# Have you heard of that? I was the only one who didn’t know.
	# => "그것에 대해 들어본 적 있어요? 나만 몰랐어요."
	# => casual : "너 그거 들어봤어? 나만 몰랐어."
	response = client.models.generate_content(
    model=GOOGLE_MODEL_NAME,
    contents=types.Part.from_text(text=prompt),
	)

	return {
		"message": response.text
	}

@app.get("/ad-copy")
def ad_copy(
	product:str = Query(..., description='상품명'),
	feature:str = Query(..., description='제품 특징'),
	target:str = Query('전연령', description='타겟'),
	# 1과 가까울수록 창의적
	temp:float = Query(0.8, ge=0.0, le=1.0, description='창의성 온도(0~1)')
):
	prompt = f"""
  제미나이 너는 최고의 마케터야, 너는 이 상품을 아래 조건에 맞게 효과적인 문구로 mz들에게 특히 어필할만한 창의적인 문구를 만들어줬으면해
	1. 상품명 {product}
  2. 제품특징 {feature}
	3. 타겟 {target} 
  4. 감성 + 트랜드 + 임팩트를 강조해줘
	5. 결과물 : 너가 만든 문구를 제외한 모든 표현은 생략해줘, 설명 금지 

	"""

	response = client.models.generate_content(
    model=GOOGLE_MODEL_NAME,
    contents=types.Part.from_text(text=prompt),
    config=types.GenerateContentConfig(
        temperature=temp,
        top_p=0.95,
        top_k=20,
    ),
	)
	return { 'answer' : response.text}

class Summary(BaseModel):
	text : str
	target_lan : str = "Korean"
	max_sentence : int =  3 # n문장 요약
	

@app.post("/summarize")
async def summarize(summary:Summary):
	
  prompt = f"""
  안녕 제미나이 너는 복잡한 정보를 명료하게 정리하는 전문 편집자야.
	아래 텍스트를 분석해서 {summary.target_lan}으로 요약해줘
		
  [텍스트]
	{summary.text}
	
	[작성 가이드]
	1. 핵심 내용을 최대 {summary.max_sentence}개의 문장으로 요약할 것
	2. 가장 중요한 키워드를 3개 추출할 것.
	3. 객관적이고 중립적인 어조를 유지할 것.
	
	[출력형식]
	- 요약 : (내용)
	- 키워드 : #키워드1, #키워드2, #키워드3
	"""
	
  response = client.models.generate_content(
    model=GOOGLE_MODEL_NAME,
    contents=types.Part.from_text(text=prompt),
    config=types.GenerateContentConfig(temperature=0.2),
  )
  return { 'answer' : response.text}


if __name__ == '__main__':
	uvicorn.run('main:app',host='0.0.0.0', port=8000, reload=True)