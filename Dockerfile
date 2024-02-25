# Use the official Gradle image with JDK 17 as the build stage
FROM gradle:8.0-jdk11 AS build

# Set the working directory as /home/gradle/src
WORKDIR /home/gradle/src

# Copy the project files into the container, preserving the ownership
COPY --chown=gradle:gradle . /home/gradle/src

WORKDIR /home/gradle/src

# Run the Gradle shadowJar task to create a fat JAR
RUN gradle shadowJar --no-daemon

# Use the official OpenJDK 11 image for the runtime stage
FROM openjdk:11-jdk-slim

# Expose the 8080 port for the application
EXPOSE 8080

# Create the /app directory for storing the JAR file
RUN mkdir /app

# Copy the shadow JAR from the build stage to the runtime stage
COPY --from=build /home/gradle/src/build/libs/*.jar /app/pojoservice.jar

# Set the entrypoint to run the application
ENTRYPOINT ["java", "-jar", "/app/pojoservice.jar"]