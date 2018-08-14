package com.repair;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * auther   kangwang
 */
@SpringBootApplication
@CrossOrigin//跨域访问
public class Application {
    public static void main(String []args){
        SpringApplication.run(Application.class,args);
    }
}
