package com.corn.cinema;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.corn.cinema"})
@EnableDubboConfiguration
public class CinemaApplication {

    public static void main(String[] args) {

        SpringApplication.run(CinemaApplication.class, args);
    }
}
