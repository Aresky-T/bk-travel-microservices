# Dockefile
FROM openjdk:17-alpine
# Set working directory
WORKDIR /app
# Setup maven install dependencies
COPY .mvn .mvn/
COPY mvnw pom.xml ./
RUN chmod +x mvnw
RUN ./mvnw dependency:go-offline
# Copy source app to workdir
COPY . .
# Setup timezone for server
ENV TZ=Ho_Chi_Minh
# Specify port for container
EXPOSE 8085
# CMD for start app
ENTRYPOINT ["./mvnw", "spring-boot:run"]
# Label
LABEL auth="Aresky"