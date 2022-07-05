package qrcode;

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
 * <pre>
 * The greeting service definition.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.19.0)",
    comments = "Source: scan.proto")
public final class ScanServiceGrpc {

  private ScanServiceGrpc() {}

  public static final String SERVICE_NAME = "qrcode.ScanService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<qrcode.ScanOuterClass.CreateScanRequest,
      qrcode.ScanOuterClass.Scan> getCreateScanMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateScan",
      requestType = qrcode.ScanOuterClass.CreateScanRequest.class,
      responseType = qrcode.ScanOuterClass.Scan.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<qrcode.ScanOuterClass.CreateScanRequest,
      qrcode.ScanOuterClass.Scan> getCreateScanMethod() {
    io.grpc.MethodDescriptor<qrcode.ScanOuterClass.CreateScanRequest, qrcode.ScanOuterClass.Scan> getCreateScanMethod;
    if ((getCreateScanMethod = ScanServiceGrpc.getCreateScanMethod) == null) {
      synchronized (ScanServiceGrpc.class) {
        if ((getCreateScanMethod = ScanServiceGrpc.getCreateScanMethod) == null) {
          ScanServiceGrpc.getCreateScanMethod = getCreateScanMethod = 
              io.grpc.MethodDescriptor.<qrcode.ScanOuterClass.CreateScanRequest, qrcode.ScanOuterClass.Scan>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "qrcode.ScanService", "CreateScan"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  qrcode.ScanOuterClass.CreateScanRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  qrcode.ScanOuterClass.Scan.getDefaultInstance()))
                  .setSchemaDescriptor(new ScanServiceMethodDescriptorSupplier("CreateScan"))
                  .build();
          }
        }
     }
     return getCreateScanMethod;
  }

  private static volatile io.grpc.MethodDescriptor<qrcode.ScanOuterClass.GetScanRequest,
      qrcode.ScanOuterClass.Scan> getGetScanMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetScan",
      requestType = qrcode.ScanOuterClass.GetScanRequest.class,
      responseType = qrcode.ScanOuterClass.Scan.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<qrcode.ScanOuterClass.GetScanRequest,
      qrcode.ScanOuterClass.Scan> getGetScanMethod() {
    io.grpc.MethodDescriptor<qrcode.ScanOuterClass.GetScanRequest, qrcode.ScanOuterClass.Scan> getGetScanMethod;
    if ((getGetScanMethod = ScanServiceGrpc.getGetScanMethod) == null) {
      synchronized (ScanServiceGrpc.class) {
        if ((getGetScanMethod = ScanServiceGrpc.getGetScanMethod) == null) {
          ScanServiceGrpc.getGetScanMethod = getGetScanMethod = 
              io.grpc.MethodDescriptor.<qrcode.ScanOuterClass.GetScanRequest, qrcode.ScanOuterClass.Scan>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "qrcode.ScanService", "GetScan"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  qrcode.ScanOuterClass.GetScanRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  qrcode.ScanOuterClass.Scan.getDefaultInstance()))
                  .setSchemaDescriptor(new ScanServiceMethodDescriptorSupplier("GetScan"))
                  .build();
          }
        }
     }
     return getGetScanMethod;
  }

  private static volatile io.grpc.MethodDescriptor<qrcode.ScanOuterClass.ListScanRequest,
      qrcode.ScanOuterClass.ListScanReply> getListScanMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ListScan",
      requestType = qrcode.ScanOuterClass.ListScanRequest.class,
      responseType = qrcode.ScanOuterClass.ListScanReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<qrcode.ScanOuterClass.ListScanRequest,
      qrcode.ScanOuterClass.ListScanReply> getListScanMethod() {
    io.grpc.MethodDescriptor<qrcode.ScanOuterClass.ListScanRequest, qrcode.ScanOuterClass.ListScanReply> getListScanMethod;
    if ((getListScanMethod = ScanServiceGrpc.getListScanMethod) == null) {
      synchronized (ScanServiceGrpc.class) {
        if ((getListScanMethod = ScanServiceGrpc.getListScanMethod) == null) {
          ScanServiceGrpc.getListScanMethod = getListScanMethod = 
              io.grpc.MethodDescriptor.<qrcode.ScanOuterClass.ListScanRequest, qrcode.ScanOuterClass.ListScanReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "qrcode.ScanService", "ListScan"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  qrcode.ScanOuterClass.ListScanRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  qrcode.ScanOuterClass.ListScanReply.getDefaultInstance()))
                  .setSchemaDescriptor(new ScanServiceMethodDescriptorSupplier("ListScan"))
                  .build();
          }
        }
     }
     return getListScanMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ScanServiceStub newStub(io.grpc.Channel channel) {
    return new ScanServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ScanServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new ScanServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ScanServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new ScanServiceFutureStub(channel);
  }

  /**
   * <pre>
   * The greeting service definition.
   * </pre>
   */
  public static abstract class ScanServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void createScan(qrcode.ScanOuterClass.CreateScanRequest request,
        io.grpc.stub.StreamObserver<qrcode.ScanOuterClass.Scan> responseObserver) {
      asyncUnimplementedUnaryCall(getCreateScanMethod(), responseObserver);
    }

    /**
     */
    public void getScan(qrcode.ScanOuterClass.GetScanRequest request,
        io.grpc.stub.StreamObserver<qrcode.ScanOuterClass.Scan> responseObserver) {
      asyncUnimplementedUnaryCall(getGetScanMethod(), responseObserver);
    }

    /**
     */
    public void listScan(qrcode.ScanOuterClass.ListScanRequest request,
        io.grpc.stub.StreamObserver<qrcode.ScanOuterClass.ListScanReply> responseObserver) {
      asyncUnimplementedUnaryCall(getListScanMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getCreateScanMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                qrcode.ScanOuterClass.CreateScanRequest,
                qrcode.ScanOuterClass.Scan>(
                  this, METHODID_CREATE_SCAN)))
          .addMethod(
            getGetScanMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                qrcode.ScanOuterClass.GetScanRequest,
                qrcode.ScanOuterClass.Scan>(
                  this, METHODID_GET_SCAN)))
          .addMethod(
            getListScanMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                qrcode.ScanOuterClass.ListScanRequest,
                qrcode.ScanOuterClass.ListScanReply>(
                  this, METHODID_LIST_SCAN)))
          .build();
    }
  }

  /**
   * <pre>
   * The greeting service definition.
   * </pre>
   */
  public static final class ScanServiceStub extends io.grpc.stub.AbstractStub<ScanServiceStub> {
    private ScanServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ScanServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ScanServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ScanServiceStub(channel, callOptions);
    }

    /**
     */
    public void createScan(qrcode.ScanOuterClass.CreateScanRequest request,
        io.grpc.stub.StreamObserver<qrcode.ScanOuterClass.Scan> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCreateScanMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getScan(qrcode.ScanOuterClass.GetScanRequest request,
        io.grpc.stub.StreamObserver<qrcode.ScanOuterClass.Scan> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetScanMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void listScan(qrcode.ScanOuterClass.ListScanRequest request,
        io.grpc.stub.StreamObserver<qrcode.ScanOuterClass.ListScanReply> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getListScanMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * The greeting service definition.
   * </pre>
   */
  public static final class ScanServiceBlockingStub extends io.grpc.stub.AbstractStub<ScanServiceBlockingStub> {
    private ScanServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ScanServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ScanServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ScanServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public qrcode.ScanOuterClass.Scan createScan(qrcode.ScanOuterClass.CreateScanRequest request) {
      return blockingUnaryCall(
          getChannel(), getCreateScanMethod(), getCallOptions(), request);
    }

    /**
     */
    public qrcode.ScanOuterClass.Scan getScan(qrcode.ScanOuterClass.GetScanRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetScanMethod(), getCallOptions(), request);
    }

    /**
     */
    public qrcode.ScanOuterClass.ListScanReply listScan(qrcode.ScanOuterClass.ListScanRequest request) {
      return blockingUnaryCall(
          getChannel(), getListScanMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * The greeting service definition.
   * </pre>
   */
  public static final class ScanServiceFutureStub extends io.grpc.stub.AbstractStub<ScanServiceFutureStub> {
    private ScanServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ScanServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ScanServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ScanServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<qrcode.ScanOuterClass.Scan> createScan(
        qrcode.ScanOuterClass.CreateScanRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getCreateScanMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<qrcode.ScanOuterClass.Scan> getScan(
        qrcode.ScanOuterClass.GetScanRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetScanMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<qrcode.ScanOuterClass.ListScanReply> listScan(
        qrcode.ScanOuterClass.ListScanRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getListScanMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CREATE_SCAN = 0;
  private static final int METHODID_GET_SCAN = 1;
  private static final int METHODID_LIST_SCAN = 2;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final ScanServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(ScanServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CREATE_SCAN:
          serviceImpl.createScan((qrcode.ScanOuterClass.CreateScanRequest) request,
              (io.grpc.stub.StreamObserver<qrcode.ScanOuterClass.Scan>) responseObserver);
          break;
        case METHODID_GET_SCAN:
          serviceImpl.getScan((qrcode.ScanOuterClass.GetScanRequest) request,
              (io.grpc.stub.StreamObserver<qrcode.ScanOuterClass.Scan>) responseObserver);
          break;
        case METHODID_LIST_SCAN:
          serviceImpl.listScan((qrcode.ScanOuterClass.ListScanRequest) request,
              (io.grpc.stub.StreamObserver<qrcode.ScanOuterClass.ListScanReply>) responseObserver);
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

  private static abstract class ScanServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ScanServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return qrcode.ScanOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("ScanService");
    }
  }

  private static final class ScanServiceFileDescriptorSupplier
      extends ScanServiceBaseDescriptorSupplier {
    ScanServiceFileDescriptorSupplier() {}
  }

  private static final class ScanServiceMethodDescriptorSupplier
      extends ScanServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    ScanServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (ScanServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ScanServiceFileDescriptorSupplier())
              .addMethod(getCreateScanMethod())
              .addMethod(getGetScanMethod())
              .addMethod(getListScanMethod())
              .build();
        }
      }
    }
    return result;
  }
}
