# predictor.py

import json
from titus.genpy import PFAEngine

class PythonPredictor:
    def __init__(self, config):
        # Opening JSON file 
        f = open('./iris-pfa.json',)   
        # returns JSON object as a dictionary 
        pfaDocument = json.load(f)
        self.engine, = PFAEngine.fromJson(pfaDocument)
        # Closing file 
        f.close()

    def predict(self, payload):

        label = self.engine.action({
            "sepal_length_cm": payload["sepal_length"], 
            "sepal_width_cm": payload["sepal_width"],
            "petal_length_cm": payload["petal_length"],
            "petal_width_cm": payload["petal_width"],
            "class": "Iris-setosa"})

        return label
