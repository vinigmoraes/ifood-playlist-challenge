FROM openjdk:8-jdk-alpine

RUN mkdir /app

WORKDIR /app

COPY build/libs/tracks-api-1.0-SNAPSHOT-all.jar /app/tracks-api-1.0-SNAPSHOT-all.jar

CMD ["sh", "-c", "java -jar tracks-api-1.0-SNAPSHOT-all.jar"]