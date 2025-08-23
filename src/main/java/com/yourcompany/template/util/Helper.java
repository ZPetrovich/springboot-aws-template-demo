package com.yourcompany.template.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class Helper {

    private final ThreadLocalRandom rnd = ThreadLocalRandom.current();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public String retrieveImage(String json) {
        JsonNode pages;
        try {
            pages = objectMapper.readTree(json).path("query").path("pages");
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

//        var urls = new ArrayList<String>();
//        pages.properties().forEach(e -> {
//            var ii = e.getValue().path("imageinfo");
//            if (ii.isArray() && !ii.isEmpty()) {
//                var url = ii.get(0).path("url").asText();
//                if (!url.isBlank()) {
//                    urls.add(url);
//                }
//            }
//        });
        var urls = pages.properties().stream()
                .map(Map.Entry::getValue)
                .map(n -> n.path("imageinfo"))
                .filter(JsonNode::isArray)
                .filter(a -> !a.isEmpty())
                .map(a -> a.get(0).path("url").asText())
                .filter(s -> !s.isBlank())
                .toList();

        if (urls.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No image URLs");

        return urls.get(rnd.nextInt(urls.size()));
    }
}
