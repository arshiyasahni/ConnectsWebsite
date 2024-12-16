package com.sep.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminReport {

    @JsonProperty("jobs_count")
    private JobsCount jobsCount;

    @JsonProperty("users_count")
    private UsersCount usersCount;
}
