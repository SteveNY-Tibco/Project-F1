FROM rpy2/rpy2

#RUN apk add build-base
#RUN apk add python3-dev
#RUN apk add R

#RUN python -m pip install --upgrade pip

ENV GRPC_PYTHON_VERSION 1.26.0
RUN pip install grpcio==${GRPC_PYTHON_VERSION} grpcio-tools==${GRPC_PYTHON_VERSION} grpcio-reflection==${GRPC_PYTHON_VERSION} grpcio-health-checking==${GRPC_PYTHON_VERSION} grpcio-testing==${GRPC_PYTHON_VERSION}

RUN ln -sfn /bin/python3 /bin/python

COPY ./server /app
WORKDIR /app

RUN pip install -r requirements.txt

RUN chmod +x ./r_pkg_install.sh
RUN ./r_pkg_install.sh e1071

#EXPOSE 5000
CMD python ./server.py