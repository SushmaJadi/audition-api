package com.audition.web;

import com.audition.model.AuditionPost;
import com.audition.service.AuditionService;

import java.io.IOException;
import java.util.List;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuditionController {

    @Autowired
    AuditionService auditionService;

    // TODO Add a query param that allows data filtering. The intent of the filter is at developers discretion.
    @RequestMapping(value = "/posts", method = RequestMethod.GET, produces = {"application/json"})

    public @ResponseBody List<AuditionPost> getPosts() throws IOException {

        // TODO Add logic that filters response data based on the query param
        return auditionService.getPosts();
    }

    @RequestMapping(value = "/posts/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody AuditionPost getPosts(@Valid @PathVariable("id") final String postId) {

        final AuditionPost auditionPosts = auditionService.getPostById(postId);
        // TODO Add input validation

        return auditionPosts;
    }

    // TODO Add additional methods to return comments for each post. Hint: Check https://jsonplaceholder.typicode.com/

}
