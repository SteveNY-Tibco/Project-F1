FROM quantumlib/cirq

ENV GRPC_PYTHON_VERSION 1.26.0
RUN pip3 install grpcio==${GRPC_PYTHON_VERSION} grpcio-tools==${GRPC_PYTHON_VERSION} grpcio-reflection==${GRPC_PYTHON_VERSION} grpcio-health-checking==${GRPC_PYTHON_VERSION} grpcio-testing==${GRPC_PYTHON_VERSION}

RUN pip3 install sympy 

COPY ./server /app
WORKDIR /app

CMD python ./server.py