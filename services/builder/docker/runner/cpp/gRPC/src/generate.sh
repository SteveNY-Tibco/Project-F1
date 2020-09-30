protoc -I ../proto --grpc_out=. --plugin=protoc-gen-grpc=`which grpc_cpp_plugin` ../proto/pipecoupler.proto
protoc -I ../proto --cpp_out=. ../proto/pipecoupler.proto