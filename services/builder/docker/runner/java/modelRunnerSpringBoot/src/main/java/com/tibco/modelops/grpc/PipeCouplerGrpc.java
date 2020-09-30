package com.tibco.modelops.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.16.1)",
    comments = "Source: pipecoupler.proto")
public final class PipeCouplerGrpc {

  private PipeCouplerGrpc() {}

  public static final String SERVICE_NAME = "pipecoupler.PipeCoupler";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.tibco.modelops.grpc.Data,
      com.tibco.modelops.grpc.Reply> getHandleDataMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "HandleData",
      requestType = com.tibco.modelops.grpc.Data.class,
      responseType = com.tibco.modelops.grpc.Reply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.tibco.modelops.grpc.Data,
      com.tibco.modelops.grpc.Reply> getHandleDataMethod() {
    io.grpc.MethodDescriptor<com.tibco.modelops.grpc.Data, com.tibco.modelops.grpc.Reply> getHandleDataMethod;
    if ((getHandleDataMethod = PipeCouplerGrpc.getHandleDataMethod) == null) {
      synchronized (PipeCouplerGrpc.class) {
        if ((getHandleDataMethod = PipeCouplerGrpc.getHandleDataMethod) == null) {
          PipeCouplerGrpc.getHandleDataMethod = getHandleDataMethod = 
              io.grpc.MethodDescriptor.<com.tibco.modelops.grpc.Data, com.tibco.modelops.grpc.Reply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "pipecoupler.PipeCoupler", "HandleData"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.tibco.modelops.grpc.Data.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.tibco.modelops.grpc.Reply.getDefaultInstance()))
                  .setSchemaDescriptor(new PipeCouplerMethodDescriptorSupplier("HandleData"))
                  .build();
          }
        }
     }
     return getHandleDataMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static PipeCouplerStub newStub(io.grpc.Channel channel) {
    return new PipeCouplerStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static PipeCouplerBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new PipeCouplerBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static PipeCouplerFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new PipeCouplerFutureStub(channel);
  }

  /**
   */
  public static abstract class PipeCouplerImplBase implements io.grpc.BindableService {

    /**
     */
    public void handleData(com.tibco.modelops.grpc.Data request,
        io.grpc.stub.StreamObserver<com.tibco.modelops.grpc.Reply> responseObserver) {
      asyncUnimplementedUnaryCall(getHandleDataMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getHandleDataMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.tibco.modelops.grpc.Data,
                com.tibco.modelops.grpc.Reply>(
                  this, METHODID_HANDLE_DATA)))
          .build();
    }
  }

  /**
   */
  public static final class PipeCouplerStub extends io.grpc.stub.AbstractStub<PipeCouplerStub> {
    private PipeCouplerStub(io.grpc.Channel channel) {
      super(channel);
    }

    private PipeCouplerStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PipeCouplerStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new PipeCouplerStub(channel, callOptions);
    }

    /**
     */
    public void handleData(com.tibco.modelops.grpc.Data request,
        io.grpc.stub.StreamObserver<com.tibco.modelops.grpc.Reply> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getHandleDataMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class PipeCouplerBlockingStub extends io.grpc.stub.AbstractStub<PipeCouplerBlockingStub> {
    private PipeCouplerBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private PipeCouplerBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PipeCouplerBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new PipeCouplerBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.tibco.modelops.grpc.Reply handleData(com.tibco.modelops.grpc.Data request) {
      return blockingUnaryCall(
          getChannel(), getHandleDataMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class PipeCouplerFutureStub extends io.grpc.stub.AbstractStub<PipeCouplerFutureStub> {
    private PipeCouplerFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private PipeCouplerFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PipeCouplerFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new PipeCouplerFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.tibco.modelops.grpc.Reply> handleData(
        com.tibco.modelops.grpc.Data request) {
      return futureUnaryCall(
          getChannel().newCall(getHandleDataMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_HANDLE_DATA = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final PipeCouplerImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(PipeCouplerImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_HANDLE_DATA:
          serviceImpl.handleData((com.tibco.modelops.grpc.Data) request,
              (io.grpc.stub.StreamObserver<com.tibco.modelops.grpc.Reply>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class PipeCouplerBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    PipeCouplerBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.tibco.modelops.grpc.PipeCouplerProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("PipeCoupler");
    }
  }

  private static final class PipeCouplerFileDescriptorSupplier
      extends PipeCouplerBaseDescriptorSupplier {
    PipeCouplerFileDescriptorSupplier() {}
  }

  private static final class PipeCouplerMethodDescriptorSupplier
      extends PipeCouplerBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    PipeCouplerMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (PipeCouplerGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new PipeCouplerFileDescriptorSupplier())
              .addMethod(getHandleDataMethod())
              .build();
        }
      }
    }
    return result;
  }
}
