FROM maven:3.9.5-eclipse-temurin-17 AS build

WORKDIR /app

COPY pom.xml .

RUN mvn dependency:go-offline

COPY src ./src

RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

COPY --from=build /app/target/api-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 9090

ENTRYPOINT ["java", "--add-opens", "java.base/sun.nio.ch=ALL-UNNAMED", "-jar", "/app/app.jar"]
