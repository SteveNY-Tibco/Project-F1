FROM qiskit/qiskit-tutorial

RUN apt-get update
RUN apt-get -y upgrade python3
RUN apt-get -y install python3-pip

RUN pip install qiskit --upgrade

RUN pip install torch==1.3.0+cpu torchvision==0.4.1+cpu -f https://download.pytorch.org/whl/torch_stable.html

RUN python -m pip install grpcio
RUN python -m pip install grpcio-tools

ENV GRPC_PYTHON_VERSION 1.26.0
RUN pip3 install grpcio==${GRPC_PYTHON_VERSION} grpcio-tools==${GRPC_PYTHON_VERSION} grpcio-reflection==${GRPC_PYTHON_VERSION} grpcio-health-checking==${GRPC_PYTHON_VERSION} grpcio-testing==${GRPC_PYTHON_VERSION}

COPY ./server /app
WORKDIR /app

CMD python ./server.py