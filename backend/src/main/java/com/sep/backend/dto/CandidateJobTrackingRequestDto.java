package com.sep.backend.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sep.backend.models.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;


@Builder
@Getter
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CandidateJobTrackingRequestDto {

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
