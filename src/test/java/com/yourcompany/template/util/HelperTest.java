package com.yourcompany.template.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HelperTest {

    @Test
    void retrieveImageWhenImageInfoPresent() throws Exception {
        var json = """
                {
                  "query": {
                    "pages": {
                      "123": {
                        "imageinfo": [
                          { "url": "https://upload.wikimedia.org/foo.jpg" }
                        ]
                      }
                    }
                  }
                }
                """;

        Helper helper = new Helper();

        String url = helper.retrieveImage(json);

        assertEquals("https://upload.wikimedia.org/foo.jpg", url);
    }
}
