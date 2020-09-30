FROM bigoyang/tensorflow-serving-python-base

COPY ./server /app
WORKDIR /app

RUN pip install -r requirements.txt

CMD python ./server.py