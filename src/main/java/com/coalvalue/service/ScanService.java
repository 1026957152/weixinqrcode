package com.coalvalue.service;


import com.coalvalue.domain.entity.Scan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;

/**
 * Created by silence yuan on 2015/7/25.
 */
public interface ScanService extends BaseService {


    String dynamic(String id);

    Optional dynamic_by_qrcode(String content);

    Page<Scan> listScan(PageRequest of);

    Scan getScan(String id);

    Scan createScan(String occupationId, String content, String info);
}
