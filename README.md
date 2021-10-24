# sillycat-zeppelin-api
use zeppelin client to call zeppelin

### how to build it

mvn clean package

### how to deploy

place the file target/sillycat-zeppelin-api-1.0-bundle.tar.gz in right place

### how to run it
export ZEPPELIN_SERVER=http://centos7-master/zeppelin
export ZEPPELIN_USERNAME=username
export ZEPPELIN_PASSWORD=password
bin/run.sh {nodeId}
