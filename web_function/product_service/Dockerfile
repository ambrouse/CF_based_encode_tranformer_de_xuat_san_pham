FROM openjdk:17-alpine
WORKDIR /app
COPY target/*.jar app.jar
EXPOSE 9005
ENTRYPOINT ["java", "-jar", "app.jar"]