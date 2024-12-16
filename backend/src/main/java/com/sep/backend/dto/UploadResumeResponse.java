package com.sep.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

/**
 * The type Upload Resume response dto.
 */
@Builder
public class UploadResumeResponse {

    @JsonProperty("email_address")
    private String emailAddress;

    @JsonProperty("uploaded_resume")
    private byte[] uploadedResume;


}
