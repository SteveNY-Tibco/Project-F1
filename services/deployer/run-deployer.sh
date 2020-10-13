#!/bin/bash

#docker run -p 10082:10082 -v /Users/steven/Applications/Project-F1:/home/projectF1  -e "FLOGO_APP_PROPS_ENV=auto" -e "System_ServiceLocatorIP=192.168.1.152" flogo/deployer:0.1.0

docker run -p 10082:10082 \
  --restart on-failure \
  --add-host $(hostname):127.0.0.1 \
  -e "FLOGO_APP_PROPS_ENV=auto" \
  -e "System_BaseFolder=/home/f1" \
  -e "System_BaseFolderExt=/Users/steven/Applications/Project-F1" \
  -e "System_ServiceLocatorIP=192.168.1.152" \
  -v /Users/steven/.kube:/root/.kube \
  -v /Users/steven/Applications/Project-F1:/home/f1 \
  --name deployer \
  flogo/deployer:0.2.0