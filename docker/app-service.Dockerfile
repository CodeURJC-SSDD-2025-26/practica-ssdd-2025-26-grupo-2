# STAGE 1: Build
FROM maven:3.9-eclipse-temurin-21 AS builder
WORKDIR /project
COPY app-service/pom.xml .
COPY app-service/src ./src
RUN mvn clean package -DskipTests

# STAGE 2: Run
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=builder /project/target/*.jar app.jar
EXPOSE 8443
ENTRYPOINT ["java", "-jar", "app.jar"]