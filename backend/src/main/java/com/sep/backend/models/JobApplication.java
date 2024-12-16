package com.sep.backend.models;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.sep.backend.enums.ApplicationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * The type Job Application.
 */
@Entity
@Table(name = "job_application")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobApplication extends BaseModel {


@Column(name = "job_id", nullable = false)
private Integer jobID;

@Column(name = "email_address", nullable = false)
private String emailAddress;

@Column(name = "employer_email", nullable = false)
private String employerEmail;

@Column(name = "application_status")
private ApplicationStatus applicationStatus;
}
