package com.sep.backend.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * The type Job Listing.
 */
@Entity
@Table(name = "job_listing")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobListing{

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "random_five_digit_id")
@GenericGenerator(name = "random_five_digit_id", strategy = "com.sep.backend.utils.IDGenerator")
@Column(name = "id", nullable = false, unique = true)
private Integer id;

@Column(name = "company", nullable = false)
private String company;

@Column(name = "tools")
@ElementCollection
private List<String> tools;

@Column(name = "languages")
@ElementCollection
private List<String> languages;

@Column(name = "salary")
private Double salary;

@Column(name = "position", nullable = false)
private String position;

@Column(name = "role")
private String role;

@Column(name = "level")
private String level;

@Column(name = "posted_at")
private Date postedAt;

@Column(name = "location", nullable = false)
private String location;

@Column(name = "contract", nullable = false)
private String contract;

@Column(name = "employer_email", nullable = false)
private String employerEmail;

@Column(name = "description")
private String description;

@Column(name = "requirements")
private String requirements;
}
