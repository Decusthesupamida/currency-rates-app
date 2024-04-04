FROM adoptopenjdk/openjdk17:alpine-jre
WORKDIR /app
COPY target/currency-exchange.jar /app/currency-exchange.jar
CMD ["java", "-jar", "currency-exchange.jar"]
