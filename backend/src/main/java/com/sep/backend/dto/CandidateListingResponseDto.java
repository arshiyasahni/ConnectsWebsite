package com.sep.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.Date;
import java.util.List;

/**
 * The type Candidate Listing response dto.
 */
@Builder
public class CandidateListingResponseDto {
    @JsonProperty("id")
    private Integer id;

    @JsonProperty("email_address")
    private String emailAddress;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("phone_number")
    private String phoneNumber;
}
