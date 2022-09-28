FROM openjdk:11-jdk-alpine
RUN apk update && apk add bash
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
COPY wait-for-it.sh .
RUN chmod +x wait-for-it.sh
#eureka depende de zipkin
ENTRYPOINT ["./wait-for-it.sh", "-t", "60","zipkin:9411", "--", "java", "-jar", "app.jar"]