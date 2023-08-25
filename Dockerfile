#
# Build stage
FROM maven:3.8.3-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests


FROM openjdk:17.0.1-jdk-slim
COPY --from=build /target/MyBank-0.0.1-SNAPSHOT.jar MyBank.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","MyBank.jar"]