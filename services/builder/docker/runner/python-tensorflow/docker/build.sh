mkdir ./server
cp -R ../../server .
docker build --tag bigoyang/tensorflow-python-model:0.2.0 .
docker push bigoyang/tensorflow-python-model:0.2.0