package com.coalvalue.repository;


import com.coalvalue.domain.entity.PermanentQrcode;
import com.coalvalue.domain.entity.WxPermanentQrcode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by zhao yuan on 01/10/2015.
 */
public interface PermanentQrcodeRepository extends JpaRepository<PermanentQrcode, Integer> {
    PermanentQrcode findByKeyAndAppId(Integer integer, String appId);

    Page<PermanentQrcode> findByObjectId(String companyId, Pageable of);

    PermanentQrcode findByObjectIdAndTypeAndAppIdAndStatus(String uuid, String text, String weixinUrlFilte_delivery, String qrcode_status_valid);

    PermanentQrcode findTop1ByTappedStatus(String text);


    PermanentQrcode findTop1ByTappedStatusAndAppId(String text, String appId);
}
