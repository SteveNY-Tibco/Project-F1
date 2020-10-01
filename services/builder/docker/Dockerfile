###################################################################################
#                         Flogo App image                         #
###################################################################################
FROM reldocker.tibco.com/tci/flogo-go-base-alpine:3.8-1.14.4

RUN apk update && apk add ca-certificates
# System setup
RUN apk add git curl build-base autoconf automake libtool

#===============================================================================
# Install protoc

ARG prefix=/usr
ENV GOBIN=/usr/bin/
ENV PROTOBUF_URL https://github.com/google/protobuf/releases/download/v3.8.0/protobuf-cpp-3.8.0.tar.gz
RUN curl -L -o /tmp/protobuf.tar.gz $PROTOBUF_URL
WORKDIR /tmp/
RUN tar xvzf protobuf.tar.gz
WORKDIR /tmp/protobuf-3.8.0
RUN ./autogen.sh && \
    ./configure --prefix=$prefix && \
    make -j 3 && \
    make check && \
    make install

# Install protoc-gen-go
RUN go get github.com/golang/protobuf/protoc-gen-go

#===============================================================================

#For FE case, set FLOGO_HTTP_SERVICE_PORT to empty to disable heathcheck
ENV FLOGO_LOG_DTFORMAT="15:04:05.000" \
    STOP_ENGINE_ON_ERROR=flase \
    FLOGO_HTTP_SERVICE_PORT="7777"

ADD ./runner /usr/flogo/runner/
ADD ./engine /opt/tibco/flogo/1.0/bin/

WORKDIR /opt/tibco/flogo/1.0/bin/

RUN chmod a+x /opt/tibco/flogo/1.0/bin/*


CMD ["/opt/tibco/flogo/1.0/bin/engine"]

VOLUME /opt/tibco/flogo/1.0/bin



EXPOSE 7777 9999