FROM frolvlad/alpine-python-machinelearning
COPY . /app
WORKDIR /app
RUN pip install -r requirements.txt
EXPOSE 5000
CMD python ./server.py