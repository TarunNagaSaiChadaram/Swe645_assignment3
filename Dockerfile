# FROM maven:3.8.5-openjdk-17 AS build
# WORKDIR /app
# COPY pom.xml .
# COPY src ./src
# RUN mvn clean package
# FROM openjdk:17-jdk-slim
# WORKDIR /app
# COPY target/stusurvey-0.0.1-SNAPSHOT.jar survey-app.jar
# EXPOSE 8080
# CMD ["java", "-jar", "survey-app.jar"]


FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/stusurvey-0.0.1-SNAPSHOT.jar survey-app.jar
ENTRYPOINT ["java", "-jar", "survey-app.jar"]
