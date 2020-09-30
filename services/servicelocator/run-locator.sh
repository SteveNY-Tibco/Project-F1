#!/bin/bash

#docker run -p 10080:10080 -p 10081:10081 -v /Users/steven/Applications/Project-F1/modelops/locator/:/home/locator flogo/servicelocator:0.1.0

docker run -p 10080:10080 -p 10081:10081 \
  --restart on-failure \
  --add-host $(hostname):127.0.0.1 \
  -e "FLOGO_APP_PROPS_ENV=auto" \
  -e "System_BaseFolder=/home/f1" \
  -e "System_ServiceLocatorIP=192.168.1.152" \
  -v /Users/steven/Applications/Project-F1:/home/f1 \
  -v /Users/steven/Applications/Project-F1/modelops/locator/:/home/locator \
  --name service-locator \
  bigoyang/service-locator:0.1.0