package com.coalvalue.repository;


import com.coalvalue.domain.entity.WxPermanentQrcode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by zhao yuan on 01/10/2015.
 */
public interface WxPermanentQrcodeRepository extends JpaRepository<WxPermanentQrcode, Integer> {
    WxPermanentQrcode findByKeyAndAppId(Integer integer, String appId);

    Page<WxPermanentQrcode> findByObjectId(String companyId, Pageable of);

    WxPermanentQrcode findByObjectIdAndTypeAndAppIdAndStatus(String uuid, String text, String weixinUrlFilte_delivery, String qrcode_status_valid);

    WxPermanentQrcode findTop1ByTappedStatus(String text);


    List<WxPermanentQrcode> findByObjectIdAndTypeAndStatus(String id, String text, String qrcode_status_valid);


    List<WxPermanentQrcode> findByKeyAndTypeAndStatus(String key, String text, String qrcode_status_valid);

    List<WxPermanentQrcode> findByKeyAndStatus(String key, String qrcode_status_valid);

    List<WxPermanentQrcode> findByUuidAndStatus(String key, String qrcode_status_valid);

    WxPermanentQrcode findByKeyAndScence(String key, String text);

}
