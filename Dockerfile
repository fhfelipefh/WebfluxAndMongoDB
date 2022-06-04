FROM openjdk:17
WORKDIR /app
COPY build/libs/WebfluxAndMongoDB.jar /app/WebfluxAndMongoDB.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "WebfluxAndMongoDB.jar"]