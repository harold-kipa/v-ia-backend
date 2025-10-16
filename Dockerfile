FROM maven:3-openjdk-17 AS build

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline -B

COPY src ./src
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim

WORKDIR /app

RUN apt-get update && apt-get install -y \
    libfontconfig1 \
    libxrender1 \
    libfreetype6 \
    fonts-dejavu-core \
    && apt-get clean

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8050

ENTRYPOINT ["java", "-jar", "app.jar", "--server.port=8050"]
