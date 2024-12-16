package com.sep.backend.models;

import com.sep.backend.enums.Gender;
import lombok.*;

import javax.persistence.*;

/**
 * The type Candidate.
 */
@Entity
@Table(name = "candidate")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Candidate extends BaseModel{

@Column(name = "first_name")
private String firstName;

@Column(name = "last_name", nullable = false)
private String lastName;

@Column(name = "middle_name")
private String middleName;

@Column(name = "phone_number", unique = true)
private String phoneNumber;

@Column(name = "email_address", unique = true, nullable = false)
private String emailAddress;

@Column(name = "gender", nullable = false)
private Gender gender;

@Column(name = "age", nullable = false)
private Integer age;

@Column(name = "password", nullable = false)
private String password;

@Column(name = "uploaded_resume")
private byte[] uploadedResume;

}
