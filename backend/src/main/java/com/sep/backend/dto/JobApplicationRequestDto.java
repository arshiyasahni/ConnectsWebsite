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
public class JobApplicationRequestDto {

    @JsonProperty("job_id")
    private Integer jobId;

    @JsonProperty("employer_email")
    private String employerEmail;

    @JsonProperty("email_address")
    private String emailAddress;


}
