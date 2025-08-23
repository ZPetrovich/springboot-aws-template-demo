package com.yourcompany.template.functions;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.yourcompany.template.service.MagicImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.function.context.config.ContextFunctionCatalogAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.function.Function;

@Component
@ComponentScan("com.yourcompany.template")  // <— critical
@Import(ContextFunctionCatalogAutoConfiguration.class)
public class ServerlessAPIFunctions {

    public static final String PATH_PARAMETERS = "pathParameters";

    public static final String KEYWORD = "id";

    @Autowired
    private MagicImageService magicImageService;

    // Function for getting data (GET equivalent)
    @Bean
    public Function<Map<String, Object>, APIGatewayProxyResponseEvent> getData() {
        return event -> {
            System.out.println("getData... " + event);

            Map<String, Object> pathParameters = (Map<String, Object>) event.get(PATH_PARAMETERS);
            String keyword = pathParameters != null ? (String) pathParameters.get(KEYWORD) : null;

            if (keyword == null) {
                throw new IllegalArgumentException("Path parameter for 'keyword' is missed!");
            }

            var randomImageUrl = magicImageService.randomImage(keyword);
            System.out.println("Image URL: " + randomImageUrl);

            return new APIGatewayProxyResponseEvent()
                    .withStatusCode(302)
                    .withHeaders(Map.of(
                            "Location", randomImageUrl,
                            "Cache-Control", "public, max-age=86400"
                    ));
        };
    }
}
