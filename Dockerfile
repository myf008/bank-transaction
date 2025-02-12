#FROM eclipse-temurin:11-jdk
#WORKDIR /app
#COPY target/bank-0.0.1-SNAPSHOT.jar app.jar
#ENTRYPOINT ["java", "-jar", "app.jar"]

#FROM amazoncorretto:11
FROM openjdk:11-jre
WORKDIR /app
COPY target/*.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]