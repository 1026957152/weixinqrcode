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
    comments = "Source: qrcode.proto")
public final class QrcodeGrpc {

  private QrcodeGrpc() {}

  public static final String SERVICE_NAME = "qrcode.Qrcode";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<qrcode.QrcodeOuterClass.QrcodeRequest,
      qrcode.QrcodeOuterClass.QrcodeReply> getCreateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Create",
      requestType = qrcode.QrcodeOuterClass.QrcodeRequest.class,
      responseType = qrcode.QrcodeOuterClass.QrcodeReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<qrcode.QrcodeOuterClass.QrcodeRequest,
      qrcode.QrcodeOuterClass.QrcodeReply> getCreateMethod() {
    io.grpc.MethodDescriptor<qrcode.QrcodeOuterClass.QrcodeRequest, qrcode.QrcodeOuterClass.QrcodeReply> getCreateMethod;
    if ((getCreateMethod = QrcodeGrpc.getCreateMethod) == null) {
      synchronized (QrcodeGrpc.class) {
        if ((getCreateMethod = QrcodeGrpc.getCreateMethod) == null) {
          QrcodeGrpc.getCreateMethod = getCreateMethod = 
              io.grpc.MethodDescriptor.<qrcode.QrcodeOuterClass.QrcodeRequest, qrcode.QrcodeOuterClass.QrcodeReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "qrcode.Qrcode", "Create"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  qrcode.QrcodeOuterClass.QrcodeRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  qrcode.QrcodeOuterClass.QrcodeReply.getDefaultInstance()))
                  .setSchemaDescriptor(new QrcodeMethodDescriptorSupplier("Create"))
                  .build();
          }
        }
     }
     return getCreateMethod;
  }

  private static volatile io.grpc.MethodDescriptor<qrcode.QrcodeOuterClass.QrcodeRequest,
      qrcode.QrcodeOuterClass.QrcodeReply> getGetMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Get",
      requestType = qrcode.QrcodeOuterClass.QrcodeRequest.class,
      responseType = qrcode.QrcodeOuterClass.QrcodeReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<qrcode.QrcodeOuterClass.QrcodeRequest,
      qrcode.QrcodeOuterClass.QrcodeReply> getGetMethod() {
    io.grpc.MethodDescriptor<qrcode.QrcodeOuterClass.QrcodeRequest, qrcode.QrcodeOuterClass.QrcodeReply> getGetMethod;
    if ((getGetMethod = QrcodeGrpc.getGetMethod) == null) {
      synchronized (QrcodeGrpc.class) {
        if ((getGetMethod = QrcodeGrpc.getGetMethod) == null) {
          QrcodeGrpc.getGetMethod = getGetMethod = 
              io.grpc.MethodDescriptor.<qrcode.QrcodeOuterClass.QrcodeRequest, qrcode.QrcodeOuterClass.QrcodeReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "qrcode.Qrcode", "Get"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  qrcode.QrcodeOuterClass.QrcodeRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  qrcode.QrcodeOuterClass.QrcodeReply.getDefaultInstance()))
                  .setSchemaDescriptor(new QrcodeMethodDescriptorSupplier("Get"))
                  .build();
          }
        }
     }
     return getGetMethod;
  }

  private static volatile io.grpc.MethodDescriptor<qrcode.QrcodeOuterClass.QrcodeRequest,
      qrcode.QrcodeOuterClass.QrcodeReply> getGetbykeyMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Getbykey",
      requestType = qrcode.QrcodeOuterClass.QrcodeRequest.class,
      responseType = qrcode.QrcodeOuterClass.QrcodeReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<qrcode.QrcodeOuterClass.QrcodeRequest,
      qrcode.QrcodeOuterClass.QrcodeReply> getGetbykeyMethod() {
    io.grpc.MethodDescriptor<qrcode.QrcodeOuterClass.QrcodeRequest, qrcode.QrcodeOuterClass.QrcodeReply> getGetbykeyMethod;
    if ((getGetbykeyMethod = QrcodeGrpc.getGetbykeyMethod) == null) {
      synchronized (QrcodeGrpc.class) {
        if ((getGetbykeyMethod = QrcodeGrpc.getGetbykeyMethod) == null) {
          QrcodeGrpc.getGetbykeyMethod = getGetbykeyMethod = 
              io.grpc.MethodDescriptor.<qrcode.QrcodeOuterClass.QrcodeRequest, qrcode.QrcodeOuterClass.QrcodeReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "qrcode.Qrcode", "Getbykey"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  qrcode.QrcodeOuterClass.QrcodeRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  qrcode.QrcodeOuterClass.QrcodeReply.getDefaultInstance()))
                  .setSchemaDescriptor(new QrcodeMethodDescriptorSupplier("Getbykey"))
                  .build();
          }
        }
     }
     return getGetbykeyMethod;
  }

  private static volatile io.grpc.MethodDescriptor<qrcode.QrcodeOuterClass.QrcodeRequest,
      qrcode.QrcodeOuterClass.QrcodeReply> getDynamicMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Dynamic",
      requestType = qrcode.QrcodeOuterClass.QrcodeRequest.class,
      responseType = qrcode.QrcodeOuterClass.QrcodeReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<qrcode.QrcodeOuterClass.QrcodeRequest,
      qrcode.QrcodeOuterClass.QrcodeReply> getDynamicMethod() {
    io.grpc.MethodDescriptor<qrcode.QrcodeOuterClass.QrcodeRequest, qrcode.QrcodeOuterClass.QrcodeReply> getDynamicMethod;
    if ((getDynamicMethod = QrcodeGrpc.getDynamicMethod) == null) {
      synchronized (QrcodeGrpc.class) {
        if ((getDynamicMethod = QrcodeGrpc.getDynamicMethod) == null) {
          QrcodeGrpc.getDynamicMethod = getDynamicMethod = 
              io.grpc.MethodDescriptor.<qrcode.QrcodeOuterClass.QrcodeRequest, qrcode.QrcodeOuterClass.QrcodeReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "qrcode.Qrcode", "Dynamic"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  qrcode.QrcodeOuterClass.QrcodeRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  qrcode.QrcodeOuterClass.QrcodeReply.getDefaultInstance()))
                  .setSchemaDescriptor(new QrcodeMethodDescriptorSupplier("Dynamic"))
                  .build();
          }
        }
     }
     return getDynamicMethod;
  }

  private static volatile io.grpc.MethodDescriptor<qrcode.QrcodeOuterClass.CreateQrRequest,
      qrcode.QrcodeOuterClass.Qr> getCreateQrMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateQr",
      requestType = qrcode.QrcodeOuterClass.CreateQrRequest.class,
      responseType = qrcode.QrcodeOuterClass.Qr.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<qrcode.QrcodeOuterClass.CreateQrRequest,
      qrcode.QrcodeOuterClass.Qr> getCreateQrMethod() {
    io.grpc.MethodDescriptor<qrcode.QrcodeOuterClass.CreateQrRequest, qrcode.QrcodeOuterClass.Qr> getCreateQrMethod;
    if ((getCreateQrMethod = QrcodeGrpc.getCreateQrMethod) == null) {
      synchronized (QrcodeGrpc.class) {
        if ((getCreateQrMethod = QrcodeGrpc.getCreateQrMethod) == null) {
          QrcodeGrpc.getCreateQrMethod = getCreateQrMethod = 
              io.grpc.MethodDescriptor.<qrcode.QrcodeOuterClass.CreateQrRequest, qrcode.QrcodeOuterClass.Qr>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "qrcode.Qrcode", "CreateQr"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  qrcode.QrcodeOuterClass.CreateQrRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  qrcode.QrcodeOuterClass.Qr.getDefaultInstance()))
                  .setSchemaDescriptor(new QrcodeMethodDescriptorSupplier("CreateQr"))
                  .build();
          }
        }
     }
     return getCreateQrMethod;
  }

  private static volatile io.grpc.MethodDescriptor<qrcode.QrcodeOuterClass.GetQrRequest,
      qrcode.QrcodeOuterClass.Qr> getGetQrMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetQr",
      requestType = qrcode.QrcodeOuterClass.GetQrRequest.class,
      responseType = qrcode.QrcodeOuterClass.Qr.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<qrcode.QrcodeOuterClass.GetQrRequest,
      qrcode.QrcodeOuterClass.Qr> getGetQrMethod() {
    io.grpc.MethodDescriptor<qrcode.QrcodeOuterClass.GetQrRequest, qrcode.QrcodeOuterClass.Qr> getGetQrMethod;
    if ((getGetQrMethod = QrcodeGrpc.getGetQrMethod) == null) {
      synchronized (QrcodeGrpc.class) {
        if ((getGetQrMethod = QrcodeGrpc.getGetQrMethod) == null) {
          QrcodeGrpc.getGetQrMethod = getGetQrMethod = 
              io.grpc.MethodDescriptor.<qrcode.QrcodeOuterClass.GetQrRequest, qrcode.QrcodeOuterClass.Qr>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "qrcode.Qrcode", "GetQr"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  qrcode.QrcodeOuterClass.GetQrRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  qrcode.QrcodeOuterClass.Qr.getDefaultInstance()))
                  .setSchemaDescriptor(new QrcodeMethodDescriptorSupplier("GetQr"))
                  .build();
          }
        }
     }
     return getGetQrMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static QrcodeStub newStub(io.grpc.Channel channel) {
    return new QrcodeStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static QrcodeBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new QrcodeBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static QrcodeFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new QrcodeFutureStub(channel);
  }

  /**
   * <pre>
   * The greeting service definition.
   * </pre>
   */
  public static abstract class QrcodeImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Sends a greeting
     * </pre>
     */
    public void create(qrcode.QrcodeOuterClass.QrcodeRequest request,
        io.grpc.stub.StreamObserver<qrcode.QrcodeOuterClass.QrcodeReply> responseObserver) {
      asyncUnimplementedUnaryCall(getCreateMethod(), responseObserver);
    }

    /**
     */
    public void get(qrcode.QrcodeOuterClass.QrcodeRequest request,
        io.grpc.stub.StreamObserver<qrcode.QrcodeOuterClass.QrcodeReply> responseObserver) {
      asyncUnimplementedUnaryCall(getGetMethod(), responseObserver);
    }

    /**
     */
    public void getbykey(qrcode.QrcodeOuterClass.QrcodeRequest request,
        io.grpc.stub.StreamObserver<qrcode.QrcodeOuterClass.QrcodeReply> responseObserver) {
      asyncUnimplementedUnaryCall(getGetbykeyMethod(), responseObserver);
    }

    /**
     */
    public void dynamic(qrcode.QrcodeOuterClass.QrcodeRequest request,
        io.grpc.stub.StreamObserver<qrcode.QrcodeOuterClass.QrcodeReply> responseObserver) {
      asyncUnimplementedUnaryCall(getDynamicMethod(), responseObserver);
    }

    /**
     */
    public void createQr(qrcode.QrcodeOuterClass.CreateQrRequest request,
        io.grpc.stub.StreamObserver<qrcode.QrcodeOuterClass.Qr> responseObserver) {
      asyncUnimplementedUnaryCall(getCreateQrMethod(), responseObserver);
    }

    /**
     */
    public void getQr(qrcode.QrcodeOuterClass.GetQrRequest request,
        io.grpc.stub.StreamObserver<qrcode.QrcodeOuterClass.Qr> responseObserver) {
      asyncUnimplementedUnaryCall(getGetQrMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getCreateMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                qrcode.QrcodeOuterClass.QrcodeRequest,
                qrcode.QrcodeOuterClass.QrcodeReply>(
                  this, METHODID_CREATE)))
          .addMethod(
            getGetMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                qrcode.QrcodeOuterClass.QrcodeRequest,
                qrcode.QrcodeOuterClass.QrcodeReply>(
                  this, METHODID_GET)))
          .addMethod(
            getGetbykeyMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                qrcode.QrcodeOuterClass.QrcodeRequest,
                qrcode.QrcodeOuterClass.QrcodeReply>(
                  this, METHODID_GETBYKEY)))
          .addMethod(
            getDynamicMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                qrcode.QrcodeOuterClass.QrcodeRequest,
                qrcode.QrcodeOuterClass.QrcodeReply>(
                  this, METHODID_DYNAMIC)))
          .addMethod(
            getCreateQrMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                qrcode.QrcodeOuterClass.CreateQrRequest,
                qrcode.QrcodeOuterClass.Qr>(
                  this, METHODID_CREATE_QR)))
          .addMethod(
            getGetQrMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                qrcode.QrcodeOuterClass.GetQrRequest,
                qrcode.QrcodeOuterClass.Qr>(
                  this, METHODID_GET_QR)))
          .build();
    }
  }

  /**
   * <pre>
   * The greeting service definition.
   * </pre>
   */
  public static final class QrcodeStub extends io.grpc.stub.AbstractStub<QrcodeStub> {
    private QrcodeStub(io.grpc.Channel channel) {
      super(channel);
    }

    private QrcodeStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected QrcodeStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new QrcodeStub(channel, callOptions);
    }

    /**
     * <pre>
     * Sends a greeting
     * </pre>
     */
    public void create(qrcode.QrcodeOuterClass.QrcodeRequest request,
        io.grpc.stub.StreamObserver<qrcode.QrcodeOuterClass.QrcodeReply> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCreateMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void get(qrcode.QrcodeOuterClass.QrcodeRequest request,
        io.grpc.stub.StreamObserver<qrcode.QrcodeOuterClass.QrcodeReply> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getbykey(qrcode.QrcodeOuterClass.QrcodeRequest request,
        io.grpc.stub.StreamObserver<qrcode.QrcodeOuterClass.QrcodeReply> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetbykeyMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void dynamic(qrcode.QrcodeOuterClass.QrcodeRequest request,
        io.grpc.stub.StreamObserver<qrcode.QrcodeOuterClass.QrcodeReply> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDynamicMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void createQr(qrcode.QrcodeOuterClass.CreateQrRequest request,
        io.grpc.stub.StreamObserver<qrcode.QrcodeOuterClass.Qr> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCreateQrMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getQr(qrcode.QrcodeOuterClass.GetQrRequest request,
        io.grpc.stub.StreamObserver<qrcode.QrcodeOuterClass.Qr> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetQrMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * The greeting service definition.
   * </pre>
   */
  public static final class QrcodeBlockingStub extends io.grpc.stub.AbstractStub<QrcodeBlockingStub> {
    private QrcodeBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private QrcodeBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected QrcodeBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new QrcodeBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * Sends a greeting
     * </pre>
     */
    public qrcode.QrcodeOuterClass.QrcodeReply create(qrcode.QrcodeOuterClass.QrcodeRequest request) {
      return blockingUnaryCall(
          getChannel(), getCreateMethod(), getCallOptions(), request);
    }

    /**
     */
    public qrcode.QrcodeOuterClass.QrcodeReply get(qrcode.QrcodeOuterClass.QrcodeRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetMethod(), getCallOptions(), request);
    }

    /**
     */
    public qrcode.QrcodeOuterClass.QrcodeReply getbykey(qrcode.QrcodeOuterClass.QrcodeRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetbykeyMethod(), getCallOptions(), request);
    }

    /**
     */
    public qrcode.QrcodeOuterClass.QrcodeReply dynamic(qrcode.QrcodeOuterClass.QrcodeRequest request) {
      return blockingUnaryCall(
          getChannel(), getDynamicMethod(), getCallOptions(), request);
    }

    /**
     */
    public qrcode.QrcodeOuterClass.Qr createQr(qrcode.QrcodeOuterClass.CreateQrRequest request) {
      return blockingUnaryCall(
          getChannel(), getCreateQrMethod(), getCallOptions(), request);
    }

    /**
     */
    public qrcode.QrcodeOuterClass.Qr getQr(qrcode.QrcodeOuterClass.GetQrRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetQrMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * The greeting service definition.
   * </pre>
   */
  public static final class QrcodeFutureStub extends io.grpc.stub.AbstractStub<QrcodeFutureStub> {
    private QrcodeFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private QrcodeFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected QrcodeFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new QrcodeFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * Sends a greeting
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<qrcode.QrcodeOuterClass.QrcodeReply> create(
        qrcode.QrcodeOuterClass.QrcodeRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getCreateMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<qrcode.QrcodeOuterClass.QrcodeReply> get(
        qrcode.QrcodeOuterClass.QrcodeRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<qrcode.QrcodeOuterClass.QrcodeReply> getbykey(
        qrcode.QrcodeOuterClass.QrcodeRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetbykeyMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<qrcode.QrcodeOuterClass.QrcodeReply> dynamic(
        qrcode.QrcodeOuterClass.QrcodeRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getDynamicMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<qrcode.QrcodeOuterClass.Qr> createQr(
        qrcode.QrcodeOuterClass.CreateQrRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getCreateQrMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<qrcode.QrcodeOuterClass.Qr> getQr(
        qrcode.QrcodeOuterClass.GetQrRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetQrMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CREATE = 0;
  private static final int METHODID_GET = 1;
  private static final int METHODID_GETBYKEY = 2;
  private static final int METHODID_DYNAMIC = 3;
  private static final int METHODID_CREATE_QR = 4;
  private static final int METHODID_GET_QR = 5;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final QrcodeImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(QrcodeImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CREATE:
          serviceImpl.create((qrcode.QrcodeOuterClass.QrcodeRequest) request,
              (io.grpc.stub.StreamObserver<qrcode.QrcodeOuterClass.QrcodeReply>) responseObserver);
          break;
        case METHODID_GET:
          serviceImpl.get((qrcode.QrcodeOuterClass.QrcodeRequest) request,
              (io.grpc.stub.StreamObserver<qrcode.QrcodeOuterClass.QrcodeReply>) responseObserver);
          break;
        case METHODID_GETBYKEY:
          serviceImpl.getbykey((qrcode.QrcodeOuterClass.QrcodeRequest) request,
              (io.grpc.stub.StreamObserver<qrcode.QrcodeOuterClass.QrcodeReply>) responseObserver);
          break;
        case METHODID_DYNAMIC:
          serviceImpl.dynamic((qrcode.QrcodeOuterClass.QrcodeRequest) request,
              (io.grpc.stub.StreamObserver<qrcode.QrcodeOuterClass.QrcodeReply>) responseObserver);
          break;
        case METHODID_CREATE_QR:
          serviceImpl.createQr((qrcode.QrcodeOuterClass.CreateQrRequest) request,
              (io.grpc.stub.StreamObserver<qrcode.QrcodeOuterClass.Qr>) responseObserver);
          break;
        case METHODID_GET_QR:
          serviceImpl.getQr((qrcode.QrcodeOuterClass.GetQrRequest) request,
              (io.grpc.stub.StreamObserver<qrcode.QrcodeOuterClass.Qr>) responseObserver);
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

  private static abstract class QrcodeBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    QrcodeBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return qrcode.QrcodeOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Qrcode");
    }
  }

  private static final class QrcodeFileDescriptorSupplier
      extends QrcodeBaseDescriptorSupplier {
    QrcodeFileDescriptorSupplier() {}
  }

  private static final class QrcodeMethodDescriptorSupplier
      extends QrcodeBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    QrcodeMethodDescriptorSupplier(String methodName) {
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
      synchronized (QrcodeGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new QrcodeFileDescriptorSupplier())
              .addMethod(getCreateMethod())
              .addMethod(getGetMethod())
              .addMethod(getGetbykeyMethod())
              .addMethod(getDynamicMethod())
              .addMethod(getCreateQrMethod())
              .addMethod(getGetQrMethod())
              .build();
        }
      }
    }
    return result;
  }
}
