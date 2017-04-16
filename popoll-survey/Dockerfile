FROM openjdk:8-jre

COPY build/libs/popoll-survey-*.jar /app/popoll-survey.jar

WORKDIR /app

EXPOSE 8080

CMD ["java", "-jar", "popoll-survey.jar"]