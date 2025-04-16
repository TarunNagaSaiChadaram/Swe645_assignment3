
# Stage 1: Build the app using Maven
FROM maven:3.8.5-openjdk-17 AS build

# Set working directory
WORKDIR /app

# Copy pom.xml and source code
COPY pom.xml .
COPY src ./src

# Build the project and package the application
RUN mvn clean package -DskipTests

# Stage 2: Prepare the final image
FROM openjdk:17-jdk-slim

# Set the working directory for the final image
WORKDIR /app

# Copy the packaged JAR from the build stage
COPY --from=build /app/target/stusurvey-0.0.1-SNAPSHOT.jar survey-app.jar

# Expose the port the app will run on
EXPOSE 8080

# Add a non-root user to run the application
RUN useradd -ms /bin/bash appuser
USER appuser

# Add health check for monitoring
HEALTHCHECK CMD curl --fail http://localhost:8080/actuator/health || exit 1

# Set entrypoint to run the application
ENTRYPOINT ["java", "-jar", "survey-app.jar"]

