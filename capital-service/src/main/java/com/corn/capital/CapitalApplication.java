package com.corn.capital;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.corn.capital"})
@EnableDubboConfiguration
public class CapitalApplication {

    public static void main(String[] args) {

        SpringApplication.run(CapitalApplication.class, args);
    }
}

