FROM openjdk:8-jre

COPY build/libs/popoll-eureka-*.jar /app/popoll-eureka.jar

WORKDIR /app

EXPOSE 8080

CMD ["java", "-jar", "popoll-eureka.jar"]