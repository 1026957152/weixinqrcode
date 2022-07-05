package com.coalvalue.repository;


import com.coalvalue.domain.entity.Scan;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by zhao yuan on 01/10/2015.
 */
public interface ScenarioRepository extends JpaRepository<Scan, Integer> {


    Scan findByUuid(String id);



//    List<Attach> findByAttachValueAndValue(String attachValue, String attachObjectValue);


}
