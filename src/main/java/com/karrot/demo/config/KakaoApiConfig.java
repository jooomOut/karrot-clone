package com.karrot.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@PropertySource("classpath:kakao-api.properties")
public class KakaoApiConfig {

    @Value("${coordToRegionUrl}")
    private String URL;

    @Value("${restKey}")
    private String REST_KEY;

    @Bean
    public WebClient kakaoWebClient(){
        return WebClient.builder()
                .baseUrl(URL)
                .defaultHeader("Authorization", "KakaoAK "+REST_KEY)
                .build();
    }
}