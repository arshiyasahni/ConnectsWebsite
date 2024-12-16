package com.sep.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public class UsersCount {

    @JsonProperty("candidate_count")
    private int candidateCount;

    @JsonProperty("employer_count")
    private int employerCount;

}
