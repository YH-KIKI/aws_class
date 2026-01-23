FROM openjdk:21-ea-jdk-slim
# JAR 파일을 이미지에 복사 gradle
COPY build/libs/boot-0.0.1-SNAPSHOT.jar app.jar

# 3) 컨테이너 실행 시 JAR 실행
ENTRYPOINT ["java", "-jar", "app.jar"]