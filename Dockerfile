FROM openjdk:17-jdk-alpine
ADD target/tp-foyer-5.0.0.jar app.jar
EXPOSE 8089
ENTRYPOINT ["java", "-jar", "/app.jar"]