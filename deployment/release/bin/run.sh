#!/bin/sh

java -Dzeppelin.server=${ZEPPELIN_SERVER} \
     -Dzeppelin.username=${ZEPPELIN_USERNAME} \
     -Dzeppelin.password=${ZEPPELIN_PASSWORD} \
     -jar ./lib/sillycat-zeppelin-*.jar $1