mkdir ./server
cp -R ../../server .
docker build --tag bigoyang/pyspark-python-model:0.1.0 .
docker push bigoyang/pyspark-python-model:0.1.0