FROM openjdk:17-oracle
LABEL authors="vangv"
EXPOSE 8761
COPY build/libs/discovery_server-0.0.1-SNAPSHOT.jar discovery_server-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "discovery_server-0.0.1-SNAPSHOT.jar"]