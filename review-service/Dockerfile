# Stage 1: building
FROM maven:3.6.3-openjdk-17 AS building
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests
# Stage 2: running
FROM openjdk:17-alpine AS running
WORKDIR /app
COPY --from=building /app/target/*.jar app.jar
ENV TZ=Asia/Ho_Chi_Minh
EXPOSE 8089
ENTRYPOINT [ "java", "-jar", "app.jar" ]
LABEL auth="Aresky"