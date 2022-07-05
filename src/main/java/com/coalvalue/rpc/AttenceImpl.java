package com.coalvalue.rpc;





import com.coalvalue.configuration.CommonConstant;
import com.coalvalue.domain.entity.WxPermanentQrcode;
import com.coalvalue.domain.entity.WxTemporaryQrcode;
import com.coalvalue.domain.enums.WxQrcodeTypeEnum;
import com.coalvalue.repository.WxPermanentQrcodeRepository;
import com.coalvalue.service.DynamicQrcodeService;
import com.coalvalue.service.ScanIdService;
import com.coalvalue.service.TempQrcodeService;
import com.coalvalue.service.WxService;
import com.google.rpc.Code;
import com.google.rpc.Status;
import io.grpc.protobuf.StatusProto;
import io.grpc.stub.StreamObserver;

import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;
import qrcode.QrcodeGrpc;
import qrcode.QrcodeOuterClass;

import java.util.List;
import java.util.Optional;


@GrpcService
	// 实现 定义一个实现服务接口的类
	public class AttenceImpl extends QrcodeGrpc.QrcodeImplBase {

	@Autowired
	private WxService wxService;
	@Autowired
	private TempQrcodeService tempQrcodeService;
	@Autowired
	private WxPermanentQrcodeRepository wxPermanentQrcodeRepository;

    @Autowired
    private DynamicQrcodeService dynamicQrcodeService;
	@Autowired
	private ScanIdService scanIdService;


	String WeixinUrlFilte_delivery= "wx6d7f2fec44663493";;
	String WeixinUrlFilter_trade= "wx242f2ca59a3973bf";
		@Override
		public void create(QrcodeOuterClass.QrcodeRequest req, StreamObserver<QrcodeOuterClass.QrcodeReply> responseObserver) {
			System.out.println("service:"+req.getId());


            WxQrcodeTypeEnum type_enum = WxQrcodeTypeEnum.fromString(req.getScenc());

			System.out.println("service:"+type_enum.getText());


			if(req.getType().equals(QrcodeOuterClass.QrcodeType.PERMANENT)){
				try {
					WxPermanentQrcode wxPermanentQrcode_cache = wxService.createPermanentQrcode(req.getId(),req.getInfo(),type_enum,
							WeixinUrlFilte_delivery);
					System.out.println("----------------------- wxPermanentQrcode_cache" + wxPermanentQrcode_cache.toString());


					QrcodeOuterClass.QrcodeReply reply = QrcodeOuterClass.QrcodeReply.newBuilder()
							.setStatus(true)
							.setId(wxPermanentQrcode_cache.getObjectId())
							.setContent(wxPermanentQrcode_cache.getContent())
							.setKey(wxPermanentQrcode_cache.getKey().toString())
							.build();

					responseObserver.onNext(reply);
					responseObserver.onCompleted();
			     }catch (Exception e){


					e.printStackTrace();

					QrcodeOuterClass.QrcodeReply reply = QrcodeOuterClass.QrcodeReply.newBuilder()
							.setStatus(false)
							.build();



					responseObserver.onNext(reply);
					responseObserver.onCompleted();
			}

			}


			if(req.getType().equals(QrcodeOuterClass.QrcodeType.TEMP)){
				WxPermanentQrcode wxPermanentQrcode_cache = null;
				try {
					wxPermanentQrcode_cache = wxService.createPermanentQrcode(req.getId(),req.getInfo(),type_enum,
							WeixinUrlFilte_delivery);
					QrcodeOuterClass.QrcodeReply reply = QrcodeOuterClass.QrcodeReply.newBuilder()
							.setStatus(true)
							.setId(wxPermanentQrcode_cache.getObjectId())
							.setContent(wxPermanentQrcode_cache.getContent())
							.setKey(wxPermanentQrcode_cache.getKey())
							.build();



					responseObserver.onNext(reply);
					responseObserver.onCompleted();
				} catch (Exception e) {
					e.printStackTrace();
					QrcodeOuterClass.QrcodeReply reply = QrcodeOuterClass.QrcodeReply.newBuilder()
							.setStatus(false)
							.build();



					responseObserver.onNext(reply);
					responseObserver.onCompleted();
				}

			}

		}



	@Override
	public void get(QrcodeOuterClass.QrcodeRequest req, StreamObserver<QrcodeOuterClass.QrcodeReply> responseObserver) {
		System.out.println("get get get service:"+req.getId());


		WxQrcodeTypeEnum type_enum = WxQrcodeTypeEnum.fromString(req.getScenc());
		System.out.println("get get get service:"+type_enum.getText());
		if(req.getType().equals(QrcodeOuterClass.QrcodeType.PERMANENT)){

			List<WxPermanentQrcode> wxPermanentQrcode_cache =  wxPermanentQrcodeRepository.findByObjectIdAndTypeAndStatus(req.getId(),type_enum.getText(),
					 CommonConstant.QRCODE_STATUS_Valid);
			QrcodeOuterClass.QrcodeReply.Builder reply = QrcodeOuterClass.QrcodeReply.newBuilder();

			if(wxPermanentQrcode_cache.size()>0){

				reply.setStatus(true)
						.setId(wxPermanentQrcode_cache.get(0).getObjectId())
						.setContent(wxPermanentQrcode_cache.get(0).getContent())
						.setKey(wxPermanentQrcode_cache.get(0).getKey().toString())
						;

			}else{
				reply.setStatus(false);
			}

			responseObserver.onNext(reply.build());
			responseObserver.onCompleted();
		}

		if(req.getType().equals(QrcodeOuterClass.QrcodeType.TEMP)){
			WxTemporaryQrcode wxPermanentQrcode_cache = tempQrcodeService.getTempByKey(1);

			if(wxPermanentQrcode_cache!= null){
				QrcodeOuterClass.QrcodeReply reply = QrcodeOuterClass.QrcodeReply.newBuilder()


						.setStatus(true)
						.setId(wxPermanentQrcode_cache.getObjectId())
						.setContent(wxPermanentQrcode_cache.getContent())
						.setKey(wxPermanentQrcode_cache.getKey().toString())
						.build();

				responseObserver.onNext(reply);
				responseObserver.onCompleted();
			}else{
				QrcodeOuterClass.QrcodeReply reply = QrcodeOuterClass.QrcodeReply.newBuilder()
				.setStatus(false).build();
				responseObserver.onNext(reply);
				responseObserver.onCompleted();
			}
			//	sequenceGenerator.getReadyDeliveryOrderNo(req.getId())




		}



	}


    @Override
    public void dynamic(QrcodeOuterClass.QrcodeRequest req, StreamObserver<QrcodeOuterClass.QrcodeReply> responseObserver) {
        System.out.println("get get get service:"+req.getId());



        System.out.println("get get get service:"+req.getRequestType());

        if(req.getRequestType().equals(QrcodeOuterClass.RequestType.DYNAMIC_by_EMPLOYEE_ID)){
            String content = dynamicQrcodeService.dynamic(req.getId());
            QrcodeOuterClass.QrcodeReply reply = QrcodeOuterClass.QrcodeReply.newBuilder()
					.setStatus(true)

					.setId(req.getId())
                    .setContent(content)
                    .build();
            responseObserver.onNext(reply);
            responseObserver.onCompleted();
        }


		if(req.getRequestType().equals(QrcodeOuterClass.RequestType.DYNAMIC_by_QRCODE)){

			System.out.println("get DYNAMIC_by_QRCODE get service:"+req.getContent());
			Optional<String> content = dynamicQrcodeService.dynamic_by_qrcode(req.getContent());
			if(content.isPresent()){
				QrcodeOuterClass.QrcodeReply reply = QrcodeOuterClass.QrcodeReply.newBuilder()
						.setStatus(true)
						.setId(content.get())
						.setContent(req.getId())
						.build();
				responseObserver.onNext(reply);
				responseObserver.onCompleted();
			}else{
				QrcodeOuterClass.QrcodeReply reply = QrcodeOuterClass.QrcodeReply.newBuilder()
						.setStatus(false)
						.setId(req.getId())
						.build();
				responseObserver.onNext(reply);
				responseObserver.onCompleted();
			}

		}

    }



    @Override
    public void getbykey(QrcodeOuterClass.QrcodeRequest req, StreamObserver<QrcodeOuterClass.QrcodeReply> responseObserver) {
        System.out.println("get get get service:"+req.getId());
        Integer key = Integer.valueOf(req.getKey());
        WxQrcodeTypeEnum type_enum = WxQrcodeTypeEnum.fromString(req.getScenc());
        System.out.println("get get get service:"+type_enum.getText());
        if(scanIdService.permanentOrTempBykey(key).equals(QrcodeOuterClass.QrcodeType.PERMANENT)){

            WxPermanentQrcode wxPermanentQrcode_cache =  wxPermanentQrcodeRepository.findByKeyAndAppId(key,type_enum.getText());
            QrcodeOuterClass.QrcodeReply.Builder reply = QrcodeOuterClass.QrcodeReply.newBuilder();

            if(wxPermanentQrcode_cache!= null){

                reply
						.setStatus(true)
                        .setId(wxPermanentQrcode_cache.getObjectId())
                        .setContent(wxPermanentQrcode_cache.getContent())
                        .setKey(wxPermanentQrcode_cache.getKey().toString());

            }else{
                reply.setExsit(false);
            }

            responseObserver.onNext(reply.build());
            responseObserver.onCompleted();
        } else if(scanIdService.permanentOrTempBykey(key).equals(QrcodeOuterClass.QrcodeType.TEMP)){
            WxTemporaryQrcode wxPermanentQrcode_cache = tempQrcodeService.getTempByKey(key);

            //	sequenceGenerator.getReadyDeliveryOrderNo(req.getId())
            QrcodeOuterClass.QrcodeReply reply = QrcodeOuterClass.QrcodeReply.newBuilder()


					.setStatus(true)
                    .setId(wxPermanentQrcode_cache.getObjectId())
                    .setContent(wxPermanentQrcode_cache.getContent())
                    .setKey(wxPermanentQrcode_cache.getKey().toString())
                    .build();



            responseObserver.onNext(reply);
            responseObserver.onCompleted();
        }else{
			QrcodeOuterClass.QrcodeReply reply = QrcodeOuterClass.QrcodeReply.newBuilder()
					.setStatus(false)
					.build();



			responseObserver.onNext(reply);
			responseObserver.onCompleted();
		}



    }



	@Override
	public void getQr(QrcodeOuterClass.GetQrRequest req, StreamObserver<QrcodeOuterClass.Qr> responseObserver) {
		System.out.println("get get get getKey:"+req.getKey());
		System.out.println("get get get getId:"+req.getId());

		//WxQrcodeTypeEnum type_enum = WxQrcodeTypeEnum.fromString(req.getScenc());
		//System.out.println("get get get service:"+type_enum.getText());
		if(req.getType().equals(QrcodeOuterClass.QrcodeType.SELF)){
			List<WxPermanentQrcode> wxPermanentQrcode_cache =  wxPermanentQrcodeRepository.findByUuidAndStatus(req.getId(),


			//List<WxPermanentQrcode> wxPermanentQrcode_cache =  wxPermanentQrcodeRepository.findByKeyAndStatus(req.getKey(),
					CommonConstant.QRCODE_STATUS_Valid);
			QrcodeOuterClass.Qr.Builder reply = QrcodeOuterClass.Qr.newBuilder();

			if(wxPermanentQrcode_cache.size()>0){

				reply
						.setId(wxPermanentQrcode_cache.get(0).getUuid())
						.setContent(wxPermanentQrcode_cache.get(0).getContent())
						.setScenc(wxPermanentQrcode_cache.get(0).getScence())

						.setKey(wxPermanentQrcode_cache.get(0).getKey().toString())
				;

			}else{

			}
			System.out.println("result:  "+reply.toString());
			responseObserver.onNext(reply.build());
			responseObserver.onCompleted();
		}




	}

	@Override
	public void createQr(QrcodeOuterClass.CreateQrRequest req, StreamObserver<QrcodeOuterClass.Qr> responseObserver) {
		System.out.println("get get get service:"+req.getKey());
		System.out.println("service:"+req.getId());
		System.out.println("service:"+req.getScenc());
		System.out.println("service:"+req.getType());
		WxQrcodeTypeEnum scen_enum = WxQrcodeTypeEnum.fromString(req.getScenc());

		System.out.println("service:"+scen_enum.getText());


		if(req.getType().equals(QrcodeOuterClass.CreateQrRequest.QrcodeType.SELF)){
			try {
				WxPermanentQrcode wxPermanentQrcode_cache = wxService.createSelfQrcode(req.getKey(),req.getInfo(),scen_enum,
						WeixinUrlFilte_delivery);
				System.out.println("----------------------- wxPermanentQrcode_cache" + wxPermanentQrcode_cache.toString());

				QrcodeOuterClass.Qr reply = QrcodeOuterClass.Qr.newBuilder()

						.setId(wxPermanentQrcode_cache.getUuid())
						.setScenc(wxPermanentQrcode_cache.getScence())
						.setContent(wxPermanentQrcode_cache.getContent())

						.setKey(wxPermanentQrcode_cache.getKey().toString())
						.build();

				responseObserver.onNext(reply);
				responseObserver.onCompleted();
			}catch (Exception e){


				e.printStackTrace();
				com.google.rpc.Status status = Status.newBuilder()
						.setCode(Code.INTERNAL.getNumber())
						.setMessage("Email or password malformed"+"createDispatchedVehicle")
						//  .addDetails(Any.pack(registerUserResponse))
						.build();
				responseObserver.onError(StatusProto.toStatusRuntimeException(status));
			}

		}




	}
}