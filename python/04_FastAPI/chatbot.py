import numpy as np
import pandas as pd
import os
from sklearn.metrics.pairwise import cosine_similarity

def load_emb_data(model, file_path, csv_file_path = 'csv/ChatbotData.csv'):
  if os.path.exists(file_path):
    emb_data = np.load(file_path)
  else:
    print(f'{file_path} 파일이 없습니다.')
    print('새로 임베딩을 시작합니다.')
    print('질문 데이터 변환 중...')
    df = pd.read_csv(csv_file_path)
    emb_data = model.encode(df['Q'])
    print('변환 완료!')
  return emb_data


def get_chatbot_response(user_input, model, emb_data, csv_file_path = 'csv/ChatbotData.csv'):
  df = pd.read_csv(csv_file_path)
  # 코사인 유사도를 비교하기 위해 사용자 질문을 임베딩
  user_emb = model.encode([user_input])

  # 코사인 유사도를 계산
  cos_sims = cosine_similarity(user_emb, emb_data)

  # 가장 높은 유사도를 가진 인덱스(위치) 찾기
  best_idx = np.argmax(cos_sims)
  # 해당 인덱스에 있는 답변 반환
  # 선택된 답변
  answer = df.iloc[best_idx]['A']
  # 유사도
  score = cos_sims[0][best_idx]

  return answer, score
