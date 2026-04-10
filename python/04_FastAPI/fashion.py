import tensorflow as tf
import numpy as np
import cv2
from tensorflow.keras import layers, models,datasets
from tensorflow.keras.callbacks import EarlyStopping, ModelCheckpoint, ReduceLROnPlateau
from tensorflow.data import Dataset

def train_model_save_model(save_model_name:str = 'model/fashion_model.keras'):
	(X_train, y_train), (_, _) = datasets.fashion_mnist.load_data()
	
	callbacks = [
		# 조기 종료
		EarlyStopping(monitor='val_loss', patience=10, restore_best_weights=True),
		# 가장 좋았던 성능을 저장 => 중간에 중지해도 중지전까지 최고 성능을 가져옴
		ModelCheckpoint(filepath=save_model_name, monitor='val_accuracy', save_best_only=True, verbose=1),
		# 학습률을 고정 값이 아닌 상황에 따라 줄어들도록 조절
		ReduceLROnPlateau(monitor='val_loss', factor=0.5, patience=3, min_lr=1e-6)
	]

	DATASET_SIZE = 60000
	TRAIN_SIZE = int(DATASET_SIZE * 0.8)

	dataset = Dataset.from_tensor_slices((X_train, y_train))
	# 섞음 
	dataset = dataset.shuffle(buffer_size=DATASET_SIZE)
	# 훈련 데이터셋
	train_dataset = dataset.take(TRAIN_SIZE)
	# 검증 데이터셋
	val_dataset = dataset.skip(TRAIN_SIZE)

	train_dataset = train_dataset.batch(128).prefetch(tf.data.AUTOTUNE)
	val_dataset = val_dataset.batch(128).prefetch(tf.data.AUTOTUNE)
	
	data_augmentation = tf.keras.Sequential([
		tf.keras.layers.RandomFlip("horizontal"), # 좌우 반전
		tf.keras.layers.RandomRotation(0.2) # 20% 범위 내에서 무작위 회전
	])

	model = tf.keras.models.Sequential([
		tf.keras.Input((28, 28, 1)),
		# 데이터 증강 
		data_augmentation, 
		# 정규화
		tf.keras.layers.Rescaling(1./255),

		tf.keras.layers.Conv2D(32, (3,3), padding='same', activation='relu'),
		tf.keras.layers.BatchNormalization(),
		tf.keras.layers.Conv2D(32, (3,3), padding='same', activation='relu'),
		tf.keras.layers.MaxPooling2D(2,2),
		tf.keras.layers.Dropout(0.25),
		# 이 때 이미지 크기 16 x 16

		# 블록 2
		tf.keras.layers.Conv2D(64, (3,3), padding='same', activation='relu'),
		tf.keras.layers.BatchNormalization(),
		tf.keras.layers.Conv2D(64, (3,3), padding='same', activation='relu'),
		tf.keras.layers.MaxPooling2D(2,2),
		tf.keras.layers.Dropout(0.25),
		# 이 때 이미지 크기 8 x 8

		# 블록 3
		tf.keras.layers.Conv2D(128, (3,3), padding='same', activation='relu'),
		tf.keras.layers.BatchNormalization(),
		tf.keras.layers.GlobalAveragePooling2D(),

		# 분류기
		tf.keras.layers.Dense(10, activation='softmax')
	])

	# 모델 설정
	model.compile(
		optimizer='adam', 
		loss='sparse_categorical_crossentropy', 
		metrics=['accuracy']
	)

	# 학습
	model.fit(
		train_dataset,
		epochs = 30,
		verbose = 0,
		validation_data=val_dataset,
		callbacks = callbacks,
		batch_size=128,
	)

def load_model(load_model_name:str = 'model/fashion_model.keras'):
	return tf.keras.models.load_model(load_model_name)

def predict_from_upload_file(
	img_contents, 
	load_model_name:str = 'model/fashion_model.keras'):

	model = load_model(load_model_name)
	# model.summary()

	np_arr = np.frombuffer(img_contents, np.uint8)
	# 배열을 이미지로 디코딩
	img = cv2.imdecode(np_arr, cv2.IMREAD_GRAYSCALE)
	
	img = cv2.resize(img, (28, 28))/255.0
	
	select_idx = np.argmax(model.predict(np.array([img]))[0])

	class_names = [
		'T-shirt/top', 'Trouser', 'Pullover', 'Dress', 'Coat',
		'Sandal', 'Shirt', 'Sneaker', 'Bag', 'Ankle boot'
	]
	return class_names[select_idx]



if __name__ == '__main__':
	print("모듈 테스트중....")
	# train_model_save_model()
	# print("학습 완료!")
	# 로컬에서 테스타할 이미지를 생성
	(X_train, y_train), (_, _) = datasets.fashion_mnist.load_data()
	cv2.imwrite('trest.png', X_train[0])
	with open('test.png', 'rb') as f:
		data = f.read()
	print(np.argmax(predict_from_upload_file(data)))
	pass