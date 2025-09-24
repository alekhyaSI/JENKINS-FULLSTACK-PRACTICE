package com.klef.dev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class SpringBootProjectApplication  {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootProjectApplication.class, args);
        System.out.println("Food Delivery Project is Running ...");
    }

}
