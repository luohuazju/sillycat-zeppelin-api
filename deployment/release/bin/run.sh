#!/bin/sh

java -Dbuild.env=prod\
	 -jar ./lib/sillycat-zeppelin-*.jar $1