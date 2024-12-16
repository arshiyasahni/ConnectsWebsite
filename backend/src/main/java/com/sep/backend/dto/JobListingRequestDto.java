package com.sep.backend.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;
import java.util.List;


@Builder
@Getter
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class JobListingRequestDto {

    @JsonProperty("company")
    private String company;

    @JsonProperty("employer_email")
    private String employerEmail;

    @JsonProperty("salary")
    private Double salary;

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

    @JsonProperty("requirements")
    private String requirements;

}
