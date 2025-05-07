package com.audition.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;


@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class AuditionPost {

    private int userId;
    @Min(value = 1, message = " Id must need to get user details")
    private int id;
    private String title;
    private String body;

    @Override
    public String toString() {
        return "AuditionPost{" +
                "userId=" + userId +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                '}';
    }

   }
