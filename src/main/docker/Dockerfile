FROM registry.aliyuncs.com/leansw/auth-server-dep

VOLUME /tmp
ADD entrypoint.sh entrypoint.sh
ADD auth-server-1.0-SNAPSHOT.jar auth-server.jar
RUN bash -c 'touch /auth-server.jar'
RUN chmod +x entrypoint.sh
EXPOSE 8080
ENTRYPOINT ["./entrypoint.sh"]