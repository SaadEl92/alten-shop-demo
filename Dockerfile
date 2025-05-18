FROM maven:3.9.6-eclipse-temurin-21 AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests
ENTRYPOINT ["java","-jar","/app/target/alten-shop-demo-0.0.1-SNAPSHOT.jar"]