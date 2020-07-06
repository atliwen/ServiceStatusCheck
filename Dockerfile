RUN echo mvn package
FROM openjdk:8-jdk-alpine3.9
MAINTAINER 李文 - ServiceStatusCheck
ADD /servicestatuscheck-0.0.7.jar //
RUN echo "Asia/Shanghai" > /etc/timezone
ENTRYPOINT ["java", "-jar", "/servicestatuscheck-0.0.7.jar"]
