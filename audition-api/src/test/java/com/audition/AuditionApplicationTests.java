package com.audition;

import com.audition.model.AuditionPost;
import com.audition.web.AuditionController;
import io.swagger.v3.core.util.Json;
import jakarta.servlet.ServletContext;
import jakarta.validation.constraints.AssertTrue;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.net.MalformedURLException;
import java.net.URL;

@SpringBootTest(useMainMethod = SpringBootTest.UseMainMethod.ALWAYS)
@AutoConfigureMockMvc
class AuditionApplicationTests {

    // TODO implement unit test. Note that an applicant should create additional unit tests as required.

    @Autowired
    MockMvc mockMvc;



    @Autowired
    MockHttpServletRequest  request;



    @Test
    void contextLoads() throws Exception {


        MvcResult response = mockMvc.perform(MockMvcRequestBuilders.request(HttpMethod.GET,"/posts")).andExpect(result -> {
            result.getResponse();
        }).andReturn();

        Assertions.assertEquals(response.getResponse().getStatus(), HttpStatus.OK.value());
        System.out.println(response.getResponse().getContentAsString());
        //Assertions.assertEquals(response.getResponse().getOutputStream().toString(),Json.class);
    }


}
