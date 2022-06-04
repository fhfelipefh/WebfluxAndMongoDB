FROM gradle:jdk18-jammy AS build
ADD . /app
WORKDIR /app
RUN gradle build --no-daemon

FROM openjdk:17 AS runtime
COPY --from=build /app/build/libs/WebfluxAndMongoDB.jar /WebfluxAndMongoDB.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "WebfluxAndMongoDB.jar"]
