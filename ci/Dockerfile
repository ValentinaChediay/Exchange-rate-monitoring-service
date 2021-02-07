FROM alpine

FROM adoptopenjdk/openjdk11-openj9:jre-11.0.5_10_openj9-0.17.0-alpine

ADD application.jar /opt/service/

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/opt/service/application.jar"]