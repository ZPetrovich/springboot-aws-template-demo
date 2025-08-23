package com.yourcompany.template.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class MagicImageServiceIT {

    @Autowired
    MagicImageService service;

    @Test
    @Timeout(15)
    void randomImage() throws Exception {
        String url = service.randomImage("cat"); // hits real Wikimedia endpoint
        System.out.println("Retrieved Image: " + url);
        assertNotNull(url);
        assertTrue(url.startsWith("http"), "Expected HTTP(S) URL, got: " + url);
        new URL(url); // validates URL syntax
    }
}
