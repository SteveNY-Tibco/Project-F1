FROM pytorch/pytorch

#RUN apk add build-base
#RUN apk add python3-dev

#RUN python -m pip install --upgrade pip

#RUN pip install -r requirements.txt

ENV GRPC_PYTHON_VERSION 1.26.0
RUN pip install grpcio==${GRPC_PYTHON_VERSION} grpcio-tools==${GRPC_PYTHON_VERSION} grpcio-reflection==${GRPC_PYTHON_VERSION} grpcio-health-checking==${GRPC_PYTHON_VERSION} grpcio-testing==${GRPC_PYTHON_VERSION}

COPY ./server /app
WORKDIR /app

#EXPOSE 5000
CMD python ./server.py