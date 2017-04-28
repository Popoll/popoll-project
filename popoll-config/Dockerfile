FROM openjdk:8-jre

COPY build/libs/popoll-config-*.jar /app/popoll-config.jar

WORKDIR /app

EXPOSE 8080

CMD ["java", "-jar", "popoll-config.jar"]