package com.sep.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sep.backend.models.*;
import lombok.Builder;

import java.util.List;

/**
 * The type Candidate Track Job response dto.
 */
@Builder
public class CandidateJobTrackingResponseDto {

    @JsonProperty("email_address")
    private String emailAddress;

    @JsonProperty("accepted_jobs")
    private List<AcceptedJob> acceptedJobs;

    @JsonProperty("applied_jobs")
    private List<JobApplication> appliedJobs;

    @JsonProperty("rejected_jobs")
    private List<RejectedJob> rejectedJobs;

    @JsonProperty("interview_jobs")
    private List<InterviewJob> interviewJobs;

}
