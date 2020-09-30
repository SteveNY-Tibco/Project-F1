#!/usr/bin/python3
import importlib
from flask import Flask, request, jsonify
from flask_restful import Resource, Api
from sqlalchemy import create_engine
from json import dumps
from predictor import PythonPredictor

app = Flask(__name__)
api = Api(app)

module = importlib.import_module("predictor", ".")
modelRunner = module.PythonPredictor({})

class ModelRunner(Resource):
    def get(self):
        print("[ModelRunner.get] entering ... ")
        return {'Model Name': "sklearn"} 

    def post(self):
        print("[ModelRunner.post] entering ... ")
        print(request.json)
        return {
            'predict' : modelRunner.predict(request.json),
            'status' : 'success',
        }

api.add_resource(ModelRunner, '/iris-classifier')

if __name__ == '__main__':
    print("[ModelRunner.__main__] entering ... ")
    app.run(host="0.0.0.0", port=int("5000"), debug=True)