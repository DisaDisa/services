package com.EurekaDiscovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

import javax.servlet.*;
import java.io.IOException;


@EnableEurekaServer
@SpringBootApplication
@PropertySource("classpath:/eureka-server.properties")
@PropertySource("classpath:/eureka-server.yml")
public class EurekaApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaApplication.class, args);
    }
    @Bean
    Filter httpTraceFilter() {
        return new Filter() {
            @Override
            public void init(FilterConfig filterConfig) throws ServletException {

            }

            @Override
            public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
                filterChain.doFilter(servletRequest, servletResponse);
            }

            @Override
            public void destroy() {

            }
        };
    }

}


