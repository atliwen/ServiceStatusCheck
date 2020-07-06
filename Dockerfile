FROM maven:3.6.3-ibmjava-8-alpine
MAINTAINER 李文 - ServiceStatusCheck
WORKDIR /app
 # 拷贝 当前目录下所有文件到 容器 /app目录下
 COPY . .
 # 运行的构建命令
 RUN mvn package
