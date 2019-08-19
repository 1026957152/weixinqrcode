package com.coalvalue.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by Peter Xu on 01/08/2015.
 */
public abstract class BaseServiceImpl implements BaseService {

    protected transient Logger logger = LoggerFactory.getLogger(getClass());

   // @PersistenceContext
    protected EntityManager em;


}
