
FROM maven:3.9.4-eclipse-temurin-17 AS build
WORKDIR /app


COPY pom.xml ./


RUN mvn dependency:go-offline


COPY src ./src/


RUN echo "=== Project structure ===" && \
    ls -la && \
    echo "=== Content of src ===" && \
    ls -la src/

# Собираем проект
RUN mvn clean package -DskipTests

# Этап запуска
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]