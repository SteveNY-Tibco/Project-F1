mkdir ./server
cp -R ../../server .
docker build --tag bigoyang/ms-qdk:0.2.0 .
#docker push bigoyang/ms-qdk:0.2.0