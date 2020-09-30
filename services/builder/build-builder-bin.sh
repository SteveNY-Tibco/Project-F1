export FLOGO_HOME=/Users/steven/Applications/flogo/2.8.1/2.8
# Build executable for Alpine linux/amd64
/Users/steven/Applications/flogo/2.8.1/2.8/bin/builder-darwin_amd64 build -p linux/amd64 -f /Users/steven/Downloads/Builder.json -n ./docker
# Build docker image
docker build --tag bigoyang/builder:0.3.0 ./docker
docker push bigoyang/builder:0.3.0