package com.audition.integration;

import com.audition.common.exception.SystemException;
import com.audition.configuration.WebServiceConfiguration;
import com.audition.model.AuditionPost;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.audition.web.AuditionController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class AuditionIntegrationClient {


    @Autowired
    private RestTemplate restTemplate;

    Logger logger = LoggerFactory.getLogger(AuditionController.class);

    @Autowired
    WebServiceConfiguration webServiceConfiguration;

    AuditionPost auditPost;


    public List<AuditionPost> getPosts() throws IOException {
        // TODO make RestTemplate call to get Posts from https://jsonplaceholder.typicode.com/posts

        ResponseEntity<AuditionPost[]> audit = restTemplate.getForEntity("https://jsonplaceholder.typicode.com/posts", AuditionPost[].class);
        MultiValueMap<String, String> httpHeaders = new LinkedMultiValueMap<>();
        httpHeaders.add("Content-Type","application/json");
        HttpEntity<?> httpEntity = new HttpEntity<>(audit,httpHeaders);
        ResponseEntity<AuditionPost[]> responseEntity= restTemplate.exchange("https://jsonplaceholder.typicode.com/posts", HttpMethod.GET,httpEntity,AuditionPost[].class);
        List<AuditionPost> auditionPosts = List.of(responseEntity.getBody());
        webServiceConfiguration.restTemplate();

        return auditionPosts;
    }

    public AuditionPost getPostById(final String id) {
        // TODO get post by post ID call from https://jsonplaceholder.typicode.com/posts/

        try {

          ResponseEntity <AuditionPost[]> auditionPost = restTemplate.getForEntity("https://jsonplaceholder.typicode.com/posts", AuditionPost[].class);
          AuditionPost[] auditionPosts = auditionPost.getBody();
           auditPost = Arrays.stream(auditionPosts).filter(audit -> {

                if (audit.getId() == Integer.parseInt(id)) {
                    auditPost = audit;
                    try {
                        webServiceConfiguration.restTemplate();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                return true;
            }).findAny().orElse(null);

                return auditPost;

        } catch (final HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new SystemException("Cannot find a Post with id " + id, "Resource Not Found",
                    404);
            } else {
                // TODO Find a better way to handle the exception so that the original error message is not lost. Feel free to change this function.
                throw new SystemException("Unknown Error message");
            }
            }

    }

    // TODO Write a method GET comments for a post from https://jsonplaceholder.typicode.com/posts/{postId}/comments - the comments must be returned as part of the post.

    // TODO write a method. GET comments for a particular Post from https://jsonplaceholder.typicode.com/comments?postId={postId}.
    // The comments are a separate list that needs to be returned to the API consumers. Hint: this is not part of the AuditionPost pojo.
}
