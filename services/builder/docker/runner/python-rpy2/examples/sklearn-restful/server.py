#!/usr/bin/python3
import os
import importlib
from flask import Flask, request, jsonify
from flask_restful import Resource, Api
from sqlalchemy import create_engine
from json import dumps

app = Flask(__name__)
api = Api(app)
module = importlib.import_module(os.environ['PythonModel.plugin'])
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

api.add_resource(ModelRunner, os.environ['PythonModel.URI'])

if __name__ == '__main__':
    print("[ModelRunner.__main__] entering ... ")
    app.run(host="0.0.0.0", port=int(os.environ['pipecoupler_port']), debug=True)