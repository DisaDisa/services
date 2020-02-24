package com.order;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:/order.properties")
@PropertySource("classpath:/order.yml")
public class OrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(com.order.OrderApplication.class, args);
    }
}
