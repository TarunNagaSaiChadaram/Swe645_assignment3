<!-Done By,Samanvitha Matta,G01252738-->
<!--Akshaya Reddy Dundigalla,G01482843-->
<!--Tarun Naga Sai Chadaram,G01445928-->
<!--The Student Survey Spring Boot application is developed using a multi-stage Dockerfile. The first stage utilizes Maven to build the application, and the second stage uses OpenJDK 17 to run the generated JAR-->


FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/stusurvey-0.0.1-SNAPSHOT.jar survey-app.jar
ENTRYPOINT ["java", "-jar", "survey-app.jar"]
