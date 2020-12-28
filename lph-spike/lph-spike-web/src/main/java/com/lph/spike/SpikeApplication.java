package com.lph.spike;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@SpringBootApplication()
@ComponentScans(value = {@ComponentScan(value = "com.lph.spike")})
@MapperScan("com.lph.spike.mapper")
@EnableDiscoveryClient
@EnableFeignClients("com.lph")
public class SpikeApplication {
    public static void main(String[] args) {

        SpringApplication.run(SpikeApplication.class, args);
    }


}
