###################################################################################
#                         Flogo App image                         #
###################################################################################
FROM docker:19.03

RUN apk update && apk add ca-certificates

RUN apk add --no-cache iptables
RUN apk add docker-compose

# System setup
RUN apk add git curl build-base autoconf automake libtool

#For FE case, set FLOGO_HTTP_SERVICE_PORT to empty to disable heathcheck
ENV FLOGO_LOG_DTFORMAT="15:04:05.000" \
    STOP_ENGINE_ON_ERROR=flase \
    FLOGO_HTTP_SERVICE_PORT="7777"

ADD ./template /usr/flogo/template/
ADD ./engine /opt/tibco/flogo/1.0/bin/

WORKDIR /opt/tibco/flogo/1.0/bin/

RUN chmod a+x /opt/tibco/flogo/1.0/bin/*


CMD ["/opt/tibco/flogo/1.0/bin/engine"]

VOLUME /opt/tibco/flogo/1.0/bin



EXPOSE 7777 9999