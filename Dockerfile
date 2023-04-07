FROM maven:3.9-amazoncorretto-17 AS builder
WORKDIR /app
COPY . .
RUN mvn clean install -DskipTests

FROM openjdk:17-jdk-alpine
ARG JAR_FILE=/app/target/*.jar
COPY --from=builder /app/target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
