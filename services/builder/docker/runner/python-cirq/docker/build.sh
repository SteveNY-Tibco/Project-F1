mkdir ./server
cp -R ../../server .
docker build --tag bigoyang/python-cirq:0.1.0 .
docker push bigoyang/python-cirq:0.1.0