# Filename: Dockerfile
# Image base
FROM openjdk:17-alpine
# Set working directory and building environment
WORKDIR /app
COPY .mvn .mvn/
COPY mvnw pom.xml ./
# Install dependencies
RUN chmod +x mvnw
RUN ./mvnw dependency:go-offline
# Copy all file after install dependencies to /app
COPY . .
# Setup TimeZone for container server
ENV TZ=Asia/Ho_Chi_Minh
# Specify Port for container
EXPOSE 8091
# Specify cmd for start application
ENTRYPOINT ["./mvnw", "spring-boot:run"]
# Author
LABEL author="Aresky"