FROM bigoyang/python-ml-base

COPY ./server /app
WORKDIR /app

RUN pip install -r requirements.txt

CMD python ./server.py