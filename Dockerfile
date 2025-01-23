FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/SWIFT-0.0.1-SNAPSHOT.jar app.jar

COPY src/main/resources/SWIFT.csv /app/src/main/resources/SWIFT.csv
COPY src/main/resources/schema.sql /app/src/main/resources/schema.sql

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
