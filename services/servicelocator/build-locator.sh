export FLOGO_HOME=/Users/steven/Applications/flogo/2.8.1/2.8
/Users/steven/Applications/flogo/2.8.1/2.8/bin/builder-darwin_amd64 build -f /Users/steven/Downloads/ServiceLocator.json -docker -name service-locator -tag=0.1.0
docker image tag flogo/service-locator:0.1.0 bigoyang/service-locator:0.1.0
docker push bigoyang/service-locator:0.1.0