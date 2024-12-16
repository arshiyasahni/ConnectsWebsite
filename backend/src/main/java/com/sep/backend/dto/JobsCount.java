package com.sep.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public class JobsCount {

    @JsonProperty("posted_jobs")
    private int postedJobs;

    @JsonProperty("active_jobs")
    private int activeJobs;

    @JsonProperty("inactive_jobs")
    private int inactiveJobs;

}
