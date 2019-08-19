package com.coalvalue.repository;


import com.coalvalue.domain.entity.WxTemporaryQrcode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by zhao yuan on 01/10/2015.
 */
public interface WxTemporaryQrcodeRepository extends JpaRepository<WxTemporaryQrcode, Integer> {




    WxTemporaryQrcode findByScanIdAndAppId(Integer scanId, String corpid);

    Optional<WxTemporaryQrcode> findById(Integer id);


    WxTemporaryQrcode findByTicket(String verificationCode);



    WxTemporaryQrcode findByObjectUuidAndTypeAndAppIdAndStatus(String objectUuid, String wxQrcodeTypeScatteredOrder, String corpId, String qrcode_status_valid);


    WxTemporaryQrcode findByTypeAndAppIdAndStatus(String wxQrcodeTypeScatteredOrder, String corpid, String qrcode_status_valid);

}
