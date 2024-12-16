package com.sep.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sep.backend.models.*;
import lombok.Builder;

import java.util.List;

/**
 * The type Employer Track Job response dto.
 */
@Builder
public class EmployerJobTrackingResponseDto {

    @JsonProperty("employer_email")
    private String employerEmail;

    @JsonProperty("accepted_jobs")
    private List<AcceptedJob> acceptedJobs;

    @JsonProperty("posted_jobs")
    private List<JobListing> postedJobs;

    @JsonProperty("rejected_jobs")
    private List<RejectedJob> rejectedJobs;

    @JsonProperty("interview_jobs")
    private List<InterviewJob> interviewJobs;

}
