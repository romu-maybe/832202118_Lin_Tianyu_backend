package com.easytest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @ClassName RunApplication
 * @Description TODO
 * @Author Administrator
 * @Date 2023/8/30 21:42
 */
@SpringBootApplication(scanBasePackages = "com.easytest")
@MapperScan("com.easytest.mappers")
@EnableTransactionManagement
public class RunApplication {
    public static void main(String[] args) {
        SpringApplication.run(RunApplication.class,args);
    }
}
