package com.lzc.crm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.lzc.crm.dao")
@EnableScheduling //启用定时任务
public class Starter {
    public static void main(String[] args) {
        SpringApplication.run(Starter.class);
    }
}
