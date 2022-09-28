#cambiar de version de java, localmente estoy trabajando con java 11
FROM openjdk:8-jdk-alpine
RUN apk update && apk add bash
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
COPY wait-for-it.sh .
RUN chmod +x wait-for-it.sh
#depende de config-service
ENTRYPOINT ["./wait-for-it.sh", "-t", "60","config-service:8888", "--", "java", "-jar", "app.jar"]