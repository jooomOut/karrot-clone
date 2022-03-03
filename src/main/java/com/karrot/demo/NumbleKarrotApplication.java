package com.karrot.demo;

import com.karrot.demo.filter.ApiLoggingFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class NumbleKarrotApplication {

    public static void main(String[] args) {
        SpringApplication.run(NumbleKarrotApplication.class, args);
    }


    @Bean
    public FilterRegistrationBean<ApiLoggingFilter> apiLoggingFilterFilterRegistrationBean() {
        FilterRegistrationBean<ApiLoggingFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new ApiLoggingFilter());

        return filterRegistrationBean;
    }
}
