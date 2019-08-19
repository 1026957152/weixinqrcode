package com.coalvalue.configuration;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.support.incrementer.MySQLMaxValueIncrementer;

import javax.sql.DataSource;

/**
 * Created by zohu on 5/29/2015.
 */
@Configuration
public class DatabaseConfig {

    @Bean
    @Primary
    @ConfigurationProperties(prefix="spring.datasource")

    public DataSource dataSource(){
        return DataSourceBuilder.create().build();

    }








    @Bean(name = "wxTemporaryQrcodeScanIdGenerator")
    public MySQLMaxValueIncrementer getWxTemporaryQrcodeScanIdGenerator() {
        MySQLMaxValueIncrementer mySQLMaxValueIncrementer = new MySQLMaxValueIncrementer();
        mySQLMaxValueIncrementer.setColumnName("gen_wx_temporary_qrcode");
        mySQLMaxValueIncrementer.setDataSource(dataSource());
        //    mySQLMaxValueIncrementer.setCacheSize(100000);
        mySQLMaxValueIncrementer.setIncrementerName("tb_generator");
        mySQLMaxValueIncrementer.setPaddingLength(8);
        return mySQLMaxValueIncrementer;
    }


    @Bean(name = "wxPermanentQrcodeScanIdGenerator")
    public MySQLMaxValueIncrementer getWxPermanentQrcodeScanIdGenerator() {
        MySQLMaxValueIncrementer mySQLMaxValueIncrementer = new MySQLMaxValueIncrementer();
        mySQLMaxValueIncrementer.setColumnName("gen_wx_permanent_qrcode");
        mySQLMaxValueIncrementer.setDataSource(dataSource());
        //    mySQLMaxValueIncrementer.setCacheSize(100000);
        mySQLMaxValueIncrementer.setIncrementerName("tb_generator");
        mySQLMaxValueIncrementer.setPaddingLength(8);
        return mySQLMaxValueIncrementer;
    }









}
