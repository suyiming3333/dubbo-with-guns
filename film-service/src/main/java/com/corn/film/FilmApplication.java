package com.corn.film;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.corn.film"})
@EnableDubboConfiguration
public class FilmApplication {

    public static void main(String[] args) {

        SpringApplication.run(FilmApplication.class, args);
    }
}
