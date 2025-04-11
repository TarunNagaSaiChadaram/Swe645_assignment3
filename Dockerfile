FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/stusurvey-0.0.1-SNAPSHOT.jar survey-app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "survey-app.jar"]
