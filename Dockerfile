# 1단계: Gradle을 사용하여 Spring Boot 애플리케이션 빌드
FROM gradle:8.1.1-jdk17 as build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon

# 2단계: 빌드된 JAR 파일을 실행하기 위한 새로운 Docker 이미지 생성
FROM openjdk:17-jdk-slim
EXPOSE 8080
RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/springboot-mongodb-0.0.1-SNAPSHOT.war /app/spring-boot-application.war
ENTRYPOINT ["java","-jar","/app/spring-boot-application.war"]
