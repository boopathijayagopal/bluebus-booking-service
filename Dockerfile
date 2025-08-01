FROM eclipse-temurin:20-jdk-alpine

WORKDIR /app

COPY target/bookingservice-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8084:8084

ENTRYPOINT ["java","-jar","app.jar"]