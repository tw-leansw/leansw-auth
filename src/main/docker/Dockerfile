FROM registry.aliyuncs.com/leansw/java-base
EXPOSE 8081
VOLUME /tmp
COPY lib /lean/java/lib
ADD entrypoint.sh /entrypoint.sh
RUN chmod +x /entrypoint.sh
ENTRYPOINT sh /entrypoint.sh
ADD auth-server-1.0-SNAPSHOT.jar /auth-server.jar
RUN touch /auth-server.jar