package com.tibco.modelops.modelRunner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.tibco.modelops.grpc.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class PipeCoupler {

  private static final Logger LOGGER =
      LoggerFactory.getLogger(PipeCoupler.class);
  

  private String sDownstreamDownstreamHost = "localhost";
  private int iDownstreamPort = 9997;
  private PipeCouplerGrpc.PipeCouplerBlockingStub pipeCouplerServiceBlockingStub;

  public void init(String sDownstreamDownstreamHost, int iDownstreamPort) {
	  if (null!=sDownstreamDownstreamHost) {
		  this.sDownstreamDownstreamHost = sDownstreamDownstreamHost;
		  this.iDownstreamPort = iDownstreamPort;
	  }
	  LOGGER.info("sDownstreamDownstreamHost = " + sDownstreamDownstreamHost);
	  LOGGER.info("iDownstreamPort = " + iDownstreamPort);
	  ManagedChannel managedChannel = ManagedChannelBuilder.forAddress(this.sDownstreamDownstreamHost, this.iDownstreamPort).usePlaintext().build();
	  pipeCouplerServiceBlockingStub = PipeCouplerGrpc.newBlockingStub(managedChannel);
  }  
    
  public Reply forwardData(Data request) {
	  LOGGER.info("gRPC client sending Data : {}", request);
		  
	  Reply reply = pipeCouplerServiceBlockingStub.handleData(request);
	  
	  LOGGER.info("gRPC client received Reply : {}", reply);

	  return reply;
  }
}