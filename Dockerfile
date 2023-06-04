FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

#24.11. Construindo a imagem Docker pelo Maven

ARG JAR_FILE

COPY target/${JAR_FILE} /app/api.jar

EXPOSE 8080

CMD ["java", "-jar", "api.jar"]

