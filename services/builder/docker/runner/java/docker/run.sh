echo $pipecoupler_port
java -classpath ./artifacts:./modelRunner-0.0.1-SNAPSHOT.jar -Dserver.port=9996 -Dgrpc.port=$pipecoupler_port com.tibco.modelops.modelRunner.ModelRunner
