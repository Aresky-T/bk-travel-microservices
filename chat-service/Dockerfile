FROM openjdk:17-alpine
WORKDIR /app
COPY .mvn .mvn/
COPY mvnw pom.xml ./
RUN chmod +x mvnw
RUN ./mvnw dependency:go-offline
COPY . .
ENV TZ=Asia/Ho_Chi_Minh
EXPOSE 8087
ENTRYPOINT [ "./mvnw", "spring-boot:run" ]
LABEL auth="Aresky"