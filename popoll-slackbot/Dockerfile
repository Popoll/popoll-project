FROM openjdk:8-jre

COPY build/libs/popoll-slackbot-*.jar /app/popoll-slackbot.jar

WORKDIR /app

EXPOSE 8080

CMD ["java", "-jar", "popoll-slackbot.jar"]