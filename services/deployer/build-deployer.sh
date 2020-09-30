export FLOGO_HOME=/Users/steven/Applications/flogo/2.8.1/2.8
/Users/steven/Applications/flogo/2.8.1/2.8/bin/builder-darwin_amd64 build -f /Users/steven/Downloads/Deployer.json -docker -tag=0.1.0
docker image tag flogo/deployer:0.1.0 bigoyang/deployer:0.1.0
docker push bigoyang/deployer:0.1.0