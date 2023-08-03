# syntax=docker/dockerfile:1

FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN chmod +x mvnw
#RUN ./mvnw dependency:resolve
# clean up the file
#RUN sed -i 's/\r$//' mvnw
# run with the SH path
#RUN mvnw dependency:resolve

COPY src ./src

CMD ["./mvnw", "dependency:resolve"]

COPY . .

EXPOSE 8080

CMD ["./mvnw", "spring-boot:run"]