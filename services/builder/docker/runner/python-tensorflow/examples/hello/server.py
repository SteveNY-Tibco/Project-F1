#!/usr/bin/python3
from flask import Flask, request, jsonify
from flask_restful import Resource, Api
from sqlalchemy import create_engine
from json import dumps

app = Flask(__name__)
api = Api(app)

class ModelRunner(Resource):
    def get(self):
        print("[ModelRunner.get] entering ... ")
        return {'employees': "# Fetches first column that is Employee ID"} 

api.add_resource(ModelRunner, '/test-1')

if __name__ == '__main__':
    print("[ModelRunner.__main__] entering ... ")
    app.run(host="0.0.0.0", port=int("5000"), debug=True)