FROM openjdk:17-oracle
LABEL authors="vangv"
EXPOSE 6002 7002
COPY target/auth_user_service-0.0.1-SNAPSHOT.jar auth_user_service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "auth_user_service-0.0.1-SNAPSHOT.jar"]