#!/usr/bin/env bash
java -Xmx512m -Djava.security.egd=file:/dev/./urandom -jar /auth-server.jar --spring.profiles.active=$ACTIVE_PROFILE