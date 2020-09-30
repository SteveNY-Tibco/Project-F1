package com.tibco.modelops.modelRunner;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tibco.modelops.grpc.Data;

import io.grpc.Server;
import io.grpc.ServerBuilder;

/**
 * Model Runner!
 *
 */
public class ModelRunner 
	extends com.tibco.modelops.grpc.PipeCouplerGrpc.PipeCouplerImplBase 
{
	private static final Logger LOGGER =
		      LoggerFactory.getLogger(ModelRunner.class);
	
	
	private PipeCoupler downstreamCoupler = null;
	
	private InferenceService model = null;
	
	private int port = 6565;
	
	public ModelRunner() {
		Map<String, String> env = System.getenv();

		String modelURI = env.get("JPMMLModel_URI");
		String modelPlugin = env.get("JPMMLModel_plugin");
		String downstreamDownstreamHosts = env.get("pipecoupler_downstreamHosts");
		String downstreamPort = env.get("pipecoupler_port");
		port = Integer.parseInt(downstreamPort);
		
		model = new InferenceService();
		Properties prop = new Properties();
		prop.setProperty("JPMMLModel_URI", modelURI);
		prop.setProperty("JPMMLModel_plugin", modelPlugin);
		try {
			model.initialize(prop);
			String sDownstreamDownstreamHost = downstreamDownstreamHosts.substring(2, downstreamDownstreamHosts.length()-2);
			int iDownstreamPort = Integer.parseInt(downstreamPort);
			downstreamCoupler = new PipeCoupler();
			downstreamCoupler.init(sDownstreamDownstreamHost, iDownstreamPort);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int getPort() {
		return port;
	}
	
	@Override
    public void handleData(com.tibco.modelops.grpc.Data request,
        io.grpc.stub.StreamObserver<com.tibco.modelops.grpc.Reply> responseObserver) {
		
		LOGGER.info("gRPC service handles Data : {}", request);
		  
		String id = request.getID();
		String sender = request.getSender();
		String content = request.getContent();
		String modelResult = null;
		
		try {
			model.evaluate(content);
			modelResult = model.getResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Data result = Data.newBuilder().setID(id).setSender(sender).setContent(modelResult).build();

		responseObserver.onNext(downstreamCoupler.forwardData(result));
		responseObserver.onCompleted();
	}
	
	public static void main(String[] args) {
		ModelRunner runner = new ModelRunner();
        Server server = ServerBuilder
          .forPort(runner.getPort())
          .addService(runner).build();
 
        try {
			server.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        try {
			server.awaitTermination();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
