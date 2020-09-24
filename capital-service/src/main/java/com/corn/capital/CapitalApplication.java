package com.corn.capital;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = {"com.corn.capital"})
@EnableDubboConfiguration
//@EnableTransactionManagement
public class CapitalApplication {

    public static void main(String[] args) {

        SpringApplication.run(CapitalApplication.class, args);
    }
}

