package com.yourcompany.template.service;

import com.yourcompany.template.util.Helper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class MagicImageService {

    private final RestClient client;
    private final Helper helper;

    MagicImageService(RestClient wikimedia, Helper helper) {
        this.client = wikimedia;
        this.helper = helper;
    }

    public String randomImage(String keyWord) {
        var json = client.get()
                .uri(u -> u.queryParam("gsrsearch", keyWord).build())
                .retrieve()
                .body(String.class);

        return helper.retrieveImage(json);
    }

}
