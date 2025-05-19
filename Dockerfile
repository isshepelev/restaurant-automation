FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean verify
RUN ls /app/target

FROM openjdk:17-oracle
ENV JAVA_OPTS="-Xmx256m"
COPY --from=build /app/target/restaurant-automation*.jar app.jar
EXPOSE 8080
CMD ["sh", "-c", "exec java -Djava.security.egd=file:/dev/./urandom $JAVA_OPTS -jar app.jar"]