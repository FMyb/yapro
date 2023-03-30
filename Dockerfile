FROM adoptopenjdk/openjdk12:latest
COPY yapro-1.0-SNAPSHOT.jar yapro-server-1.0.0.jar
ENTRYPOINT ["java","-jar","/yapro-server-1.0.0.jar"]