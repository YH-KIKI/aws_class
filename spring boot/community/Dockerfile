FROM openjdk:21-ea-jdk-slim
COPY build/libs/community-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-Xmx512m", "-jar", "app.jar"]