package com.corn.alipay;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = {"com.corn.alipay"})
//@EnableTransactionManagement
@EnableDubboConfiguration
public class AlipayApplication {

    public static void main(String[] args) {

        SpringApplication.run(AlipayApplication.class, args);
    }
}
