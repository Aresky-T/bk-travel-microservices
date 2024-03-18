# Filename: Dockerfile
FROM openjdk:17-alpine
# Install maven
# RUN apk --no-cache add maven
# Set working directory
WORKDIR /app
COPY .mvn .mvn/
COPY mvnw pom.xml ./
# Install dependencies
# RUN mvn dependency:copy-dependencies
RUN chmod +x mvnw
RUN ./mvnw dependency:go-offline
COPY . .
# RUN cp /usr/share/zoneinfo/Asia/Ho_Chi_Minh /etc/localtime && \
#     echo "Asia/Ho_Chi_Minh" > /etc/timezone
ENV TZ=Asia/Ho_Chi_Minh
# VOLUME [ "type=bind,src=./,target=/app" ]
EXPOSE 8080
ENTRYPOINT [ "./mvnw", "spring-boot:run" ]
LABEL auth="Aresky"