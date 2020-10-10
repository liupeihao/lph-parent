package com.lph;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@SpringBootApplication()
@ComponentScans(value = {@ComponentScan(value = "com.lph")})
@MapperScan("com.lph.dao")
@EnableDiscoveryClient
public class SpikeApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpikeApplication.class, args);
    }
}
