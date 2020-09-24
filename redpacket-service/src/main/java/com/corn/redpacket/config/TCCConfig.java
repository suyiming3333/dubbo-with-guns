package com.corn.redpacket.config;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@ImportResource(locations = {"classpath:tcc-transaction.xml","classpath:tcc-transaction-dubbo.xml"})
public class TCCConfig {

//    @Bean
//    public PlatformTransactionManager platformTransactionManager(@Qualifier("dataSource") DataSource dataSource){
//        return new DataSourceTransactionManager(dataSource);
//    }
}
