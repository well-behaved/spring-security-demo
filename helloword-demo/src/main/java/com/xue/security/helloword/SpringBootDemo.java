package com.xue.security.helloword;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 * @author: xuexiong@souche.com
 * @date: 2021/2/1 18:01
 * @description: 启动类
 */
@SpringBootApplication
//开启权限注解
@EnableGlobalMethodSecurity(securedEnabled = true,prePostEnabled = true)
public class SpringBootDemo {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootDemo.class, args);
    }
}
