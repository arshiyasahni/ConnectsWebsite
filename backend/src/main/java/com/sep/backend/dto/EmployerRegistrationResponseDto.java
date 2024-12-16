package com.sep.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

/**
 * The type Employer registration response dto.
 */
@Builder
public class EmployerRegistrationResponseDto {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty( "last_name")
    private String lastName;

    @JsonProperty( "company_name")
    private String companyName;

    @JsonProperty( "phone_number")
    private String phoneNumber;

    @JsonProperty( "email_address")
    private String emailAddress;

    @JsonProperty( "designation")
    private String designation;

    @JsonProperty("registration_number")
    private String registrationNumber;

}
