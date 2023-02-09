FROM eclipse-temurin:17-alpine
WORKDIR app
COPY /target/*.jar ./aticrmApp.jar
ENTRYPOINT ["java", "-jar", "./aticrmApp.jar"]