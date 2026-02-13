# Use Eclipse Temurin JDK 21 on Ubuntu Jammy as the base image
FROM eclipse-temurin:21-jdk-jammy AS base

WORKDIR /backend
COPY . .


# Stage by development purpose, you can uncomment the following line to cache Gradle dependencies
FROM base AS development

CMD ["./gradlew", "bootRun", "--no-daemon"]


# Stage 1: Build the application
FROM base AS build

# Install dependencies and build the application using Gradle
RUN ./gradlew :app:bootJar --no-daemon


# Stage 2: Create the final runtime image
FROM eclipse-temurin:21-jre-jammy AS runtime

WORKDIR /app

# Copy the built JAR file from the build stage
COPY --from=build /backend/app/build/libs/*.jar app.jar

# Expose the application port
EXPOSE 8080

# Set the entry point to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
