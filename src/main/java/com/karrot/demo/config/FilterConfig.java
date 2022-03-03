package com.karrot.demo.config;

import com.karrot.demo.filter.ApiLoggingFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@Configuration
public class FilterConfig {
    private final String LOG_REQUEST_PREFIX = "REQUEST_MSG : ";
    @Bean
    public FilterRegistrationBean<ApiLoggingFilter> apiLoggingFilterFilterRegistrationBean() {
        FilterRegistrationBean<ApiLoggingFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new ApiLoggingFilter());

        return filterRegistrationBean;
    }
    @Bean
    public CommonsRequestLoggingFilter commonsRequestLoggingFilter() {
        CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter();
        filter.setIncludeClientInfo(true);
        filter.setIncludePayload(true);
        filter.setIncludeQueryString(true);
        filter.setMaxPayloadLength(1024);

        filter.setAfterMessagePrefix(LOG_REQUEST_PREFIX);
        return filter;
    }
}
