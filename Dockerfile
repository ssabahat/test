FROM adoptopenjdk:11-jre-hotspot
COPY  target/springer-0.0.1-SNAPSHOT.jar application.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar","/application.jar"]