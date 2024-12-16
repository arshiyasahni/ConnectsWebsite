package com.sep.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sep.backend.enums.ApplicationStatus;
import lombok.Builder;

/**
 * The type Job Application response dto.
 */
@Builder
public class JobApplicationResponseDto {

    @JsonProperty("job_id")
    private Integer jobId;

    @JsonProperty("email_address")
    private String emailAddress;

    @JsonProperty("employer_email")
    private String employerEmail;

    @JsonProperty("application_status")
    private String applicationStatus;

}
