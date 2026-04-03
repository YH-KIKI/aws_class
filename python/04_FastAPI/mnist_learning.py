import os
import glob
import cv2
import numpy as np
import joblib as jl
from sklearn.svm import SVC
from sklearn.utils import shuffle
from sklearn.datasets import fetch_openml

# 지정된 폴더에 가서 이미지를 가져오고 
# 가져온 이미지를 행열배열(독립)과 값 배열들로 반환
# 이미지들은 root_path안에 라벨 폴더(답지)안에 있다고 가정


def load_image_dataset(root_path:str, width:int, height:int):

  # root_path 안에 있는 폴더명을 가져옴 -> 답지
  sub_folders = [folder for folder in os.listdir(root_path) if os.path.isdir(os.path.join(root_path,folder))]
  # print(sub_folders)

  images, labels = [], []


  # 폴더명을 이용해서 이미지들을 가져옴
  for folder in sub_folders:
    file_path = os.path.join(root_path, folder, '*.*')

    for file in glob.glob(file_path):
      # print(file)
      # 파일을 이미지로 읽음
      img = cv2.imread(file, cv2.IMREAD_GRAYSCALE)

      # 읽어온 파일 크기를 조절
      img = cv2.resize(img, (width, height))

      # 행렬로 변환해서 리스트(images)에 추가
      images.append(img.flatten())

      # 라벨을 리스트(labels)에 추가
      labels.append(str(folder))
  # numpy 리스트로 변환
  images, labels = np.array(images), np.array(labels)

  # 정규화
  images = images.astype('float32') / 255.0
  return images, labels

def train_model_save_model(X, y, file_name:str = 'mnist_model.pkl'):
  # 데이터를 섞음
  X, y = shuffle(X, y)
  # 모델선정
  model = SVC(C=1.0)
  # 학습
  model.fit(X, y)
  # 학습한 모델을 저장
  model_data = {
    'model' : model
  }
  jl.dump(model_data, file_name)
  
def load_model_predict(image: str, width:int, height:int, file_name:str = 'mnist_model.pkl'):
  model_data = jl.load(file_name)
  model = model_data['model']

  # 이미지 로드 -> 리사이즈 -> 행렬 -> 정규화 -> 예측
  img = cv2.imread(image, cv2.IMREAD_GRAYSCALE)
  img = cv2.resize(img, (width, height))
  img = img.flatten()
  img = img.astype('float32') / 255.0
  # 예측할 때 예측 데이터를 리스트로 한번 더 감싸야 함
  # 이걸 안하려면 reshape(1, -1)을 통해 1행짜리 2차원 리스트로 만들면 됨
  return model.predict([img])[0]

def download_mnist(root_path):
  mnist = fetch_openml('mnist_784', version=1, as_frame=False)
  X, y = mnist['data'], mnist['target']
  for i in range(10000):
    image = X[i].reshape(28, 28).astype(np.uint8)
    label = y[i]
    os.makedirs(os.path.join(root_path, label), exist_ok=True)
    cv2.imwrite(os.path.join(root_path, label, f'img_{i}.png'), image)


def predict_from_upload_file(img, width:int, height:int, file_name:str = 'mnist_model.pkl'):
  mode_data = jl.load(file_name)
  model = mode_data['model']

  img = cv2.resize(img, (width, height))
  img = img.flatten()
  img = img.astype('float32') / 255.0
  return model.predict([img])[0]
  

if __name__ == '__main__':
  # download_mnist('images')
  # images, labels = load_image_dataset('images', 28, 28)
  # train_model_save_model(images, labels)
  print(load_model_predict('4.png', 28, 28))
  # print(load_model_predict('day13(ml)/3.png', 28, 28))
  # print(load_model_predict('day13(ml)/4.png', 28, 28))
  pass

