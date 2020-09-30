mv ../modelRunner/target/modelRunner-0.0.1-SNAPSHOT.jar .
docker build --tag bigoyang/jpmml-runner:0.2.2 .
docker push bigoyang/jpmml-runner:0.2.2