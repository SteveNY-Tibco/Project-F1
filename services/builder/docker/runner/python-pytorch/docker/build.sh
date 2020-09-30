mkdir ./server
cp -R ../../server .
docker build --tag bigoyang/pytorch-python-model:0.1.0 .
docker push bigoyang/pytorch-python-model:0.1.0