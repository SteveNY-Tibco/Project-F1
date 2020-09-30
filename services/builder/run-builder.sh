#!/bin/bash

#docker run -p 10083:10083 -v /var/run/docker.sock:/var/run/docker.sock -v /Users/steven/Applications/Project-F1:/home/f1 -e "FLOGO_APP_PROPS_ENV=auto" -e "System_BaseFolder=/home/f1" -e "System_ServiceLocatorIP=192.168.1.152" flogo/builder:0.2.0

BIN_DIR="/Users/steven/Applications/flogo/2.8.1/2.8/bin"

DATA_DIR="${BIN_DIR}/../../data"
SERVICES_DIR_LOCAL=$( mkdir -p "${BIN_DIR}/../../services" && cd "${BIN_DIR}/../../services" && pwd )
TOOLS_DIR=$( mkdir -p "${BIN_DIR}/../../tools" && cd "${BIN_DIR}/../../tools" && pwd )
FLOGO_HOME=$( cd "${BIN_DIR}/.." && pwd )

docker run -p 10083:10083  -p 10084:10084 \
  --restart on-failure \
  --add-host $(hostname):127.0.0.1 \
  -e "FLOGO_APP_PROPS_ENV=auto" \
  -e "FLOGO_HOME=/usr/flogo/home" \
  -e "System_BaseFolder=/home/f1" \
  -e "System_ServiceLocatorIP=192.168.1.152" \
  -e "System_FlogoBuilder=builder-linux_amd64" \
  -e EULA_ACCEPT=YES \
  -e FLOGO_LIB_DIR="/usr/flogo/lib" \
  -e SERVICES_DIR=/usr/flogo/services \
  -v /var/run/docker.sock:/var/run/docker.sock \
  -v /Users/steven/Applications/Project-F1:/home/f1 \
  -v "${DATA_DIR}":/usr/flogo/data \
  -v "${TOOLS_DIR}":/usr/flogo/tools \
  -v "$BIN_DIR/../../lib":/usr/flogo/lib \
  -v "${FLOGO_HOME}":/usr/flogo/home \
  -v "${FLOGO_HOME}/lib/core":/usr/wi/go \
  -v "${FLOGO_HOME}/lib/ext":/usr/wi/ext \
  -v "${SERVICES_DIR_LOCAL}":/usr/flogo/services \
  --name builder \
  bigoyang/builder:0.3.0
