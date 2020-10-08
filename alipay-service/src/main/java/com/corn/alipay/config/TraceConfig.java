package com.corn.alipay.config;

import brave.spring.beans.TracingFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import zipkin2.reporter.AsyncReporter;
import zipkin2.reporter.okhttp3.OkHttpSender;

@Configuration
public class TraceConfig {

    @Bean
    public TracingFactoryBean tracing(){
        TracingFactoryBean tracingFactoryBean = new TracingFactoryBean();
        tracingFactoryBean.setLocalServiceName("pay-service");
        tracingFactoryBean.setSpanReporter(AsyncReporter.create(OkHttpSender.create("http://localhost:9411/api/v2/spans")));
        return tracingFactoryBean;
    }
}
