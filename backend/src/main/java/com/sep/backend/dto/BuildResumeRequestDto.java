package com.sep.backend.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class BuildResumeRequestDto {

    @JsonProperty("name")
    private String name;

    @JsonProperty("email_address")
    private String emailAddress;

    @JsonProperty("linkedIn")
    private String linkedIn;

    @JsonProperty("phone_number")
    private String phoneNumber;

    @JsonProperty("github")
    private String github;

    @JsonProperty( "skills")
    private String skills;

    @JsonProperty("exp_org1")
    private String exp_org1;

    @JsonProperty("exp_pos1")
    private String exp_pos1;

    @JsonProperty("exp_dur1")
    private String exp_dur1;

    @JsonProperty("exp_dec1")
    private String exp_dec1;

    @JsonProperty("exp_org2")
    private String exp_org2;

    @JsonProperty("exp_pos2")
    private String exp_pos2;

    @JsonProperty("exp_dur2")
    private String exp_dur2;

    @JsonProperty("exp_dec2")
    private String exp_dec2;

    @JsonProperty("proj1_title")
    private String proj1_title;

    @JsonProperty("proj1_link")
    private String proj1_link;

    @JsonProperty("proj1_desc")
    private String proj1_desc;

    @JsonProperty( "proj2_title")
    private String proj2_title;

    @JsonProperty("proj2_link")
    private String proj2_link;

    @JsonProperty("proj2_desc")
    private String proj2_desc;

    @JsonProperty("edu1_school")
    private String edu1_school;

    @JsonProperty("edu1_year")
    private String edu1_year;

    @JsonProperty("edu1_degree")
    private String edu1_degree;

    @JsonProperty("edu1_dur")
    private String edu1_dur;

    @JsonProperty("edu2_school")
    private String edu2_school;

    @JsonProperty("edu2_year")
    private String edu2_year;

    @JsonProperty( "edu2_dur")
    private String edu2_dur;

    @JsonProperty("edu2_degree")
    private String edu2_degree;

    @JsonProperty( "extra_1")
    private String extra_1;

    @JsonProperty("extra_2")
    private String extra_2;


}
