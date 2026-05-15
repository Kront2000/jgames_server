# Этап сборки
FROM maven:3.9-eclipse-temurin-17 AS build
COPY . .
RUN mvn clean package -DskipTests

# Этап запуска
FROM eclipse-temurin:17-jre-jammy
COPY --from=build /target/*.jar app.jar

ENTRYPOINT ["java", "-Xmx380M", "-jar", "/app.jar"]