package com.sep.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.Date;
import java.util.List;

/**
 * The type Job Listing response dto.
 */
@Builder
public class JobListingResponseDto {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("company")
    private String company;

    @JsonProperty("employer_email")
    private String employerEmail;

    @JsonProperty("tools")
    private List<String> tools;

    @JsonProperty("languages")
    private List<String> languages;

    @JsonProperty("position")
    private String position;

    @JsonProperty( "role")
    private String role;

    @JsonProperty("level")
    private String level;

    @JsonProperty("posted_at")
    private Date postedAt;

    @JsonProperty("contract")
    private String contract;

    @JsonProperty("location")
    private String location;

    @JsonProperty("description")
    private String description;

    @JsonProperty("salary")
    private Double salary;

    @JsonProperty("requirements")
    private String requirements;
}
