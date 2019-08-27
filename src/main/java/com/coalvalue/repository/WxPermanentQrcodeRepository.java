package com.coalvalue.repository;


import com.coalvalue.domain.entity.WxPermanentQrcode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by zhao yuan on 01/10/2015.
 */
public interface WxPermanentQrcodeRepository extends JpaRepository<WxPermanentQrcode, Integer> {
    WxPermanentQrcode findByKeyAndAppId(Integer integer, String appId);
    WxPermanentQrcode findByObjectUuidAndItemTypeAndTypeAndAppIdAndStatus(String uuid, String resourceType, String type, String appId, String qrcode_status_valid);

    Page<WxPermanentQrcode> findByObjectUuid(String companyId, Pageable of);
}
