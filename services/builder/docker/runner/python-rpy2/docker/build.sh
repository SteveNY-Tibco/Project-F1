mkdir ./server
cp -R ../../server .
docker build --tag bigoyang/rpy2-python-model:0.2.0 .
docker push bigoyang/rpy2-python-model:0.2.0