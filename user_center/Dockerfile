# 使用包含 JRE 的轻量级基础镜像
FROM openjdk:8-jre-alpine

# 设置工作目录
WORKDIR /app

# 将本地的 JAR 文件复制到容器的工作目录中
COPY user_center-0.0.1-SNAPSHOT.jar .

# 暴露应用程序运行的端口（如果已知的话）
EXPOSE 8080

# 运行 jar 文件
CMD ["java", "-jar", "/app/user_center-0.0.1-SNAPSHOT.jar", "--spring.profiles.active=prod"]