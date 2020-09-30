package com.tibco.modelops.modelRunner;

import java.util.Properties;

import org.lognet.springboot.grpc.GRpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import com.tibco.modelops.grpc.Data;

@GRpcService
public class PipeCouplerService
    extends com.tibco.modelops.grpc.PipeCouplerGrpc.PipeCouplerImplBase {
	
	private static final Logger LOGGER =
		      LoggerFactory.getLogger(PipeCouplerService.class);

	private PipeCoupler downstreamCoupler = null;
	
	private ModelWrapper model = null;
	
	@Value("#{systemEnvironment['JPMMLModel_URI']}")
	private String modelURI;
	
	@Value("#{systemEnvironment['JPMMLModel_plugin']}")
	private String modelPlugin;
	
	@Value("#{systemEnvironment['pipecoupler_downstreamHosts']}")
	private String downstreamDownstreamHosts;
	  
	@Value("#{systemEnvironment['pipecoupler_port']}")
	private String downstreamPort;

	@Override
    public void handleData(com.tibco.modelops.grpc.Data request,
        io.grpc.stub.StreamObserver<com.tibco.modelops.grpc.Reply> responseObserver) {
		
		LOGGER.info("gRPC service handles Data : {}", request);
		  
		String id = request.getID();
		String sender = request.getSender();
		String content = request.getContent();
		String modelResult = null;
		
		if (null==model) {
			model = new ModelWrapper();
			Properties prop = new Properties();
			LOGGER.info("JPMMLModel_URI = " + modelURI);
			LOGGER.info("JPMMLModel_plugin = " + modelPlugin);
			prop.setProperty("JPMMLModel_URI", modelURI);
			prop.setProperty("JPMMLModel_plugin", modelPlugin);
			try {
				model.initialize(prop);
				String sDownstreamDownstreamHost = downstreamDownstreamHosts.substring(2, downstreamDownstreamHosts.length()-2);
				int iDownstreamPort = Integer.parseInt(downstreamPort);
				LOGGER.info("sDownstreamDownstreamHost = " + sDownstreamDownstreamHost);
				LOGGER.info("iDownstreamPort = " + iDownstreamPort);
				downstreamCoupler = new PipeCoupler();
				downstreamCoupler.init(sDownstreamDownstreamHost, iDownstreamPort);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
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
}