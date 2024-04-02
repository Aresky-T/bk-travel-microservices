# Filename: Dockerfile
FROM openjdk:17-alpine
# Set working directory
WORKDIR /app
# Copy mvn, pom file for install dependencied
COPY .mvn .mvn/
COPY mvnw pom.xml ./
# Install dependencies
RUN chmod +x mvnw
RUN ./mvnw dependency:go-offline
COPY . .
# Setup TimeZone for container server
ENV TZ=Asia/Ho_Chi_Minh
# Specify Port for container
EXPOSE 8084
# Specify cmd for start application
ENTRYPOINT [ "./mvnw", "spring-boot:run" ]
# Author
LABEL author="Aresky"