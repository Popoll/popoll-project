FROM openjdk:8-jre

COPY build/libs/popoll-zuul-*.jar /app/popoll-zuul.jar

WORKDIR /app

EXPOSE 8080

CMD ["java", "-jar", "popoll-zuul.jar"]