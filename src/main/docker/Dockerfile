FROM java:8-alpine
RUN apk add --no-cache bash && \
    apk add --no-cache curl
EXPOSE 8081
COPY lib /lean/java/lib
ADD entrypoint.sh /entrypoint.sh
RUN chmod +x /entrypoint.sh
ENTRYPOINT sh /entrypoint.sh
ADD auth-server-1.0-SNAPSHOT.jar /auth-server.jar