#!/usr/bin/env bash
java -Xmx512m -Djava.security.egd=file:/dev/./urandom \
     -cp $JAVA_HOME/lib/*:/lean/java/lib/*:/auth-server.jar org.thoughtworks.lean.identity.AuthServer \
     --spring.profiles.active=$ACTIVE_PROFILE