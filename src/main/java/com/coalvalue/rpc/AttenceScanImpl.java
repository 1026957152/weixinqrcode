package com.coalvalue.rpc;


import com.coalvalue.configuration.CommonConstant;
import com.coalvalue.domain.entity.Scan;
import com.coalvalue.domain.entity.WxPermanentQrcode;
import com.coalvalue.domain.entity.WxTemporaryQrcode;
import com.coalvalue.domain.enums.WxQrcodeTypeEnum;
import com.coalvalue.repository.WxPermanentQrcodeRepository;
import com.coalvalue.service.*;
import com.google.rpc.Code;
import com.google.rpc.Status;
import io.grpc.protobuf.StatusProto;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import qrcode.QrcodeGrpc;
import qrcode.QrcodeOuterClass;
import qrcode.ScanOuterClass;
import qrcode.ScanServiceGrpc;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@GrpcService
	// 实现 定义一个实现服务接口的类
	public class AttenceScanImpl extends ScanServiceGrpc.ScanServiceImplBase {


	@Autowired
	private ScanService scanService;


	String WeixinUrlFilte_delivery= "wx6d7f2fec44663493";;
	String WeixinUrlFilter_trade= "wx242f2ca59a3973bf";


	@Override
	public void listScan(ScanOuterClass.ListScanRequest req, StreamObserver<ScanOuterClass.ListScanReply> responseObserver) {
		System.out.println("get get get service:"+req.getOccupationId());



			Page<Scan> wxPermanentQrcode_cache =  scanService.listScan(PageRequest.of(Integer.parseInt(req.getPageToken()),req.getPageSize()));
		ScanOuterClass.ListScanReply.Builder reply = ScanOuterClass.ListScanReply.newBuilder();

		reply.addAllScans(wxPermanentQrcode_cache.stream().map(e->{
			return ScanOuterClass.Scan.newBuilder().setId(e.getUuid()).build();
				}).collect(Collectors.toList()));

			responseObserver.onNext(reply.build());
			responseObserver.onCompleted();


	}







	@Override
	public void getScan(ScanOuterClass.GetScanRequest req, StreamObserver<ScanOuterClass.Scan> responseObserver) {
		System.out.println("get get get getKey:"+req.getKey());


		Scan wxPermanentQrcode_cache =  scanService.getScan(req.getId());
		ScanOuterClass.Scan.Builder reply = ScanOuterClass.Scan.newBuilder();

		reply.setId(wxPermanentQrcode_cache.getUuid());

		responseObserver.onNext(reply.build());
		responseObserver.onCompleted();

	}

	@Override
	public void createScan(ScanOuterClass.CreateScanRequest req, StreamObserver<ScanOuterClass.Scan> responseObserver) {
		System.out.println("get get get service:"+req.getOccupationId());
		System.out.println("service:"+req.getReferenceId());
		System.out.println("service:"+req.getReferenceType());
		try {
			Scan wxPermanentQrcode_cache = scanService.createScan(req.getOccupationId(),req.getReferenceId(),req.getReferenceType());
			System.out.println("----------------------- wxPermanentQrcode_cache" + wxPermanentQrcode_cache.toString());


			ScanOuterClass.Scan reply = ScanOuterClass.Scan.newBuilder()

					.setId(wxPermanentQrcode_cache.getUuid())


					.build();

			responseObserver.onNext(reply);
			responseObserver.onCompleted();
		}catch (Exception e){


			e.printStackTrace();
			Status status = Status.newBuilder()
					.setCode(Code.INTERNAL.getNumber())
					.setMessage("Email or password malformed"+"createDispatchedVehicle")
					//  .addDetails(Any.pack(registerUserResponse))
					.build();
			responseObserver.onError(StatusProto.toStatusRuntimeException(status));
		}




	}
}