# Copyright 2018 The gRPC Authors
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
"""Example gRPC server that gets/sets metadata (HTTP2 headers)"""

from __future__ import print_function
from concurrent import futures
import logging

import grpc

import pipecoupler_pb2
import pipecoupler_pb2_grpc

import os
import importlib
import json

module = importlib.import_module(os.environ['PythonModel.plugin'])
inference = module.Inference({})
downstreamHosts = os.environ['pipecoupler_downstreamHosts']
downstreamHosts = downstreamHosts[2:len(downstreamHosts)-2]
grpcport = os.environ['pipecoupler_port']

class ModelRunner(pipecoupler_pb2_grpc.PipeCouplerServicer):

    def HandleData(self, request, context):
        print('Received request: request=%s' % (request))
        
        result = inference.evaluate(json.loads(request.content))
        
        print('downstreamHosts = %s, grpcport = %s ' % (downstreamHosts, grpcport))
        with grpc.insecure_channel('%s:%s' % (downstreamHosts, grpcport)) as channel:
            stub = pipecoupler_pb2_grpc.PipeCouplerStub(channel)
            response = stub.HandleData(pipecoupler_pb2.Data(sender='you', ID='', content=json.dumps(result)))
            print("Replyed data received: " + response.content)
        
        return pipecoupler_pb2.Reply(sender='', ID='', content=response.content, status=True)


def serve():
    server = grpc.server(futures.ThreadPoolExecutor(max_workers=10))
    pipecoupler_pb2_grpc.add_PipeCouplerServicer_to_server(ModelRunner(), server)
    server.add_insecure_port('[::]:%s' % grpcport)
    server.start()
    server.wait_for_termination()


if __name__ == '__main__':
    logging.basicConfig()
    serve()
