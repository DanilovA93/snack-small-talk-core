FROM openjdk:11.0.7-jdk-slim-buster
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} egpt.jar
ENTRYPOINT ["java","-jar","/egpt.jar"]