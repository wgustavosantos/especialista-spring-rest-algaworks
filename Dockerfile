FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

COPY target/algafood-api-2.1.7.RELEASE.jar /app/api.jar

EXPOSE 8080

CMD ["java", "-jar", "api.jar"]

