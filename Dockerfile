# Use OpenJDK 17 as the base image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the tpfoyer JAR file from the target directory on your host to the container
COPY ./target/tpfoyer.jar /app/app.jar

# Expose the port that the tpfoyer application will run on
EXPOSE 8089

# Command to run the JAR file
CMD ["java", "-jar", "/app/app.jar"]

