package com.karrot.demo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlaceService {

    private final WebClient kakaoWebClient;

    public String coordToRegionCode(String x, String y){
        String ret = kakaoWebClient.get()
                .uri(uriBuilder -> uriBuilder
                        //.path(String.format("/sample/sample2/sample3"))
                        .queryParam("x", x)
                        .queryParam("y", y)
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .block();
        return ret;
    }

}
