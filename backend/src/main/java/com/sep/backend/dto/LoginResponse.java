package com.sep.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sep.backend.enums.Gender;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class LoginResponse {

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
    private Gender gender;

    @JsonProperty( "designation")
    private String designation;

    @JsonProperty( "company_name")
    private String companyName;

    @JsonProperty("age")
    private Integer age;

    @JsonProperty("logged")
    private boolean logged;

    @JsonProperty( "registration_number")
    private String registrationNumber;

}
