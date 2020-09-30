mkdir ./server
cp -R ../../server .
docker build --tag bigoyang/python-qiskit:0.1.0 .
docker push bigoyang/python-qiskit:0.1.0