package com.sep.backend.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

/**
 * The type Candidate Listing.
 */
@Entity
@Table(name = "candidate")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CandidateListing extends BaseModel{

@Column(name = "email_address", nullable = false)
private String emailAddress;

@Column(name = "last_name", nullable = false)
private String lastName;

@Column(name = "phone_number", nullable = false)
private String phoneNumber;

}
