package com.mr.logingit;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("com.mr.logingit.mapper")
@SpringBootApplication
public class LoginGitApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoginGitApplication.class, args);
    }

}
