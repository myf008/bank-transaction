#FROM amazoncorretto:11
#FROM openjdk:11-jre
#FROM eclipse-temurin:11.0.26_4-jdk-ubi9-minimal
FROM liferay/jdk11
WORKDIR /app
COPY target/*.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]