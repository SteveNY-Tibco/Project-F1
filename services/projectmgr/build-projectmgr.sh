export FLOGO_HOME=/Users/steven/Applications/flogo/2.8.1/2.8
/Users/steven/Applications/flogo/2.8.1/2.8/bin/builder-darwin_amd64 build -f /Users/steven/Downloads/ProjectMgr.json -docker -tag=0.2.0
docker image tag flogo/projectmgr:0.2.0 bigoyang/projectmgr:0.2.0
docker push bigoyang/projectmgr:0.2.0