FROM openjdk:8-jdk-alpine

RUN mkdir /app

WORKDIR /app

COPY build/libs/tracks-api-all.jar /app/tracks-api-all.jar

CMD ["sh", "-c", "java -jar tracks-api-all.jar"]