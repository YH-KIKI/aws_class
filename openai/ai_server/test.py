import sys
import os
from google import genai
from google.genai import types

def log(message:str):
  print(message)
  sys.stdout.flush()


GOOGLE_API_KEY = os.getenv("GOOGLE_API_KEY")
GOOGLE_MODEL_NAME = "gemini-2.5-flash-lite"
SYSTEM_INSTRUCTION = "당신은 내 담당 영양사야. 내가 먹은 음식들을 토대로 식단 관리해줘"
MAX_HISTORY_SIZE = 10

client = genai.Client(api_key=GOOGLE_API_KEY)

chat_history = []
chat_history.append(types.Content(role="user", parts=[types.Part.from_text(text="오늘 날씨 어때?")]))
chat_history.append(types.Content(role="user", parts=[types.Part.from_text(text="오늘은 맑고 화창하네요")]))
chat_history.append(types.Content(role="user", parts=[types.Part.from_text(text="그럼 오늘 뭐하는게 좋을까?")]))
chat_history.append(types.Content(role="user", parts=[types.Part.from_text(text="산책하는건 어때요?")]))


prompt = '그럼 어디로 갈까?'

chat_history.append(types.Content(role="user", parts=[types.Part.from_text(text=prompt)]))

response = client.models.generate_content(
  model=GOOGLE_MODEL_NAME,
  contents= chat_history, #types.Part.from_text(text=prompt),
  config=types.GenerateContentConfig(
    temperature=0.7,
    system_instruction='SYSTEM_INSTRUCTION'
	),
)
model_text = response.text

# 대화 기록을 저장(추가)
# 사용자 질문을 저장

chat_history.append(types.Content(role="model", parts=[types.Part.from_text(text=model_text)]))


# 대화 기록이 최대 저장수를 넘어가면 앞부분 제거
if(len(chat_history) >= MAX_HISTORY_SIZE):
  chat_history = chat_history[-10:] # 뒤에서 10개 추출

# 답변을 리턴

print(model_text)