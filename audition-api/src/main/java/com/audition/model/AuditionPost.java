package com.audition.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class AuditionPost {

    private int userId;
    @Nonnull
    @Min(value = 1, message = " Id must need to get user details")
    private int id;
    private String title;
    private String body;

}
