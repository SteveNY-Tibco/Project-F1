#!/bin/bash

#docker run -p 10090:10090 -v /Users/steven/Applications/Project-F1:/home/projectF1 -e "FLOGO_APP_PROPS_ENV=auto" -e "ServiceLocatorIP=192.168.1.152" flogo/projectmgr:0.1.0

docker run -p 10090:10090 -p 10091:10091 \
  --restart on-failure \
  --add-host $(hostname):127.0.0.1 \
  -e "FLOGO_APP_PROPS_ENV=auto" \
  -e "System_BaseFolder=/home/f1" \
  -e "System_ServiceLocatorIP=192.168.1.152" \
  -v /Users/steven/Applications/Project-F1:/home/f1 \
  --name projectmgr \
  bigoyang/projectmgr:0.2.0