FROM maven:3.6.3-ibmjava-8-alpine as build-stage
MAINTAINER 李文 
WORKDIR /app
 # 拷贝 当前目录下所有文件到 容器 /app目录下
COPY . .
 # 运行的构建命令  主要是设置 阿里maven镜像加速
RUN mvn package -s settings.xml

FROM openjdk:8-jdk-alpine3.9
MAINTAINER 李文 
RUN echo "Asia/Shanghai" > /etc/timezone
COPY --from=build-stage /app/target/servicestatuscheck-0.0.1.jar /
ENTRYPOINT ["java", "-jar", "/servicestatuscheck-0.0.1.jar"]
