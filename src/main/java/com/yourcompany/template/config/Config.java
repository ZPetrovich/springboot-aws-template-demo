package com.yourcompany.template.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
class Config {

    public static final String BASE_URL = "https://commons.wikimedia.org/w/api.php?action=query&generator=search&gsrnamespace=6&prop=imageinfo&iiprop=url&format=json";

    @Bean
    RestClient wikimedia(RestClient.Builder b) {
        return b.baseUrl(BASE_URL)
                .defaultHeader("User-Agent", "YourApp/1.0 (you@example.com)")
                .build();
    }
}
