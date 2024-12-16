package com.sep.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sep.backend.models.AcceptedJob;
import com.sep.backend.models.InterviewJob;
import com.sep.backend.models.JobApplication;
import com.sep.backend.models.RejectedJob;
import lombok.Builder;

import java.util.List;

/**
 * The type Admin Track Job response dto.
 */
@Builder
public class AdminJobTrackingResponseDto {

    @JsonProperty("accepted_jobs")
    private List<AcceptedJob> acceptedJobs;

    @JsonProperty("applied_jobs")
    private List<JobApplication> appliedJobs;

    @JsonProperty("rejected_jobs")
    private List<RejectedJob> rejectedJobs;

    @JsonProperty("interview_jobs")
    private List<InterviewJob> interviewJobs;

}
