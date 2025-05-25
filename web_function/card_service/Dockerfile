FROM openjdk:17-alpine
WORKDIR /app
COPY target/*.jar app.jar
EXPOSE 9006
ENTRYPOINT ["java", "-jar", "app.jar"]