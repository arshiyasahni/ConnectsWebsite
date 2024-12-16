package com.sep.backend.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

/**
 * The type Candidate registration response dto.
 */
@Builder
public class CandidateRegistrationResponseDto {

@JsonProperty("id")
private Integer id;

@JsonProperty("first_name")
private String firstName;

@JsonProperty( "last_name")
private String lastName;

@JsonProperty( "middle_name")
private String middleName;

@JsonProperty( "phone_number")
private String phoneNumber;

@JsonProperty( "email_address")
private String emailAddress;

@JsonProperty( "gender")
private String gender;

@JsonProperty("age")
private Integer age;

}
