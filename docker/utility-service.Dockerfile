# STAGE 1: Build
FROM maven:3.9-eclipse-temurin-21 AS builder
WORKDIR /project
COPY utility-service/pom.xml .
COPY utility-service/src ./src
RUN mvn clean package -DskipTests

# STAGE 2: Run
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=builder /project/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]