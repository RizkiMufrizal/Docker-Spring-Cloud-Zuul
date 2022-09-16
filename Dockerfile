FROM openjdk:8-jdk-alpine
MAINTAINER rizki mufrizal

ADD target/*.jar app.jar
RUN sh -c 'touch /app.jar'
