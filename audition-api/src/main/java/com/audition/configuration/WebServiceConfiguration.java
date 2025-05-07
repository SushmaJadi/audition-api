package com.audition.configuration;

import com.audition.model.AuditionPost;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;
import java.io.IOException;
import java.util.List;


@Configuration
public class WebServiceConfiguration implements WebMvcConfigurer {

    private List<AuditionPost> auditionPost;
    Logger logger = LoggerFactory.getLogger(WebServiceConfiguration.class);


    @Bean
    public ObjectMapper objectMapper() {
        // TODO configure Jackson Object mapper that
        //  1. allows for date format as yyyy-MM-dd
        //  2. Does not fail on unknown properties
        //  3. maps to camelCase
        //  4. Does not include null values or empty values
        //  5. does not write datas as timestamps.

        return new ObjectMapper();
    }

    @Bean
    public RestTemplate restTemplate() throws IOException {
        final RestTemplate restTemplate = new RestTemplate(
            new BufferingClientHttpRequestFactory(createClientFactory()));

        ObjectMapper objectMapper = objectMapper();
        AuditionPost[] auditPost = restTemplate.getForObject("https://jsonplaceholder.typicode.com/posts", AuditionPost[].class);
        if (auditPost != null) {
            auditionPost =List.of(auditPost);
        }
        File file  = new File( "c:/Users/sushm/audition-api/src/main/resources/files/auditPost1.json");
        objectMapper.writeValue(file,auditionPost);

        logger.info(objectMapper.writeValueAsString(auditPost));

       // TODO create a logging interceptor that logs request/response for rest template calls.

        return restTemplate;
    }

    private SimpleClientHttpRequestFactory createClientFactory() {
        final SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setOutputStreaming(false);
        return requestFactory;
    }
}
