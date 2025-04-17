/* -----------------------------------------------
# Student Survey Spring Boot Application Dockerfile
# Contributors:
# - Samanvitha Matta (G01252738)
# - Akshaya Reddy Dundigalla (G01482843)
# - Tarun Naga Sai Chadaram (G01445928)
#
// Description:
 Multi-stage Docker build:
// 1. Build the Spring Boot app using Maven and OpenJDK 17.
// 2. Use a slim OpenJDK 17 base image to run the packaged JAR.
//-----------------------------------------------
*/

FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/stusurvey-0.0.1-SNAPSHOT.jar survey-app.jar
ENTRYPOINT ["java", "-jar", "survey-app.jar"]
