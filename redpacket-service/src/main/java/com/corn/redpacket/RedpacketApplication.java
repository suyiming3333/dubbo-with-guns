package com.corn.redpacket;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = {"com.corn.redpacket"})
@EnableDubboConfiguration
public class RedpacketApplication {

    public static void main(String[] args) {

        SpringApplication.run(RedpacketApplication.class, args);
    }
}

