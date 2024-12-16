package com.sep.backend.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;


/**
 * The type Employer Track Job.
 */
@Entity
@Table(name = "employer_track_job")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployerTrackJob extends BaseModel{

@Column(name = "employer_email", nullable = false, unique = true)
private String employerEmail;

@Column(name = "accepted_jobs", nullable = false)
@OneToMany
private List<AcceptedJob> acceptedJobs;

@Column(name = "rejected_jobs", nullable = false)
@OneToMany
private List<RejectedJob> rejectedJobs;

@Column(name = "posted_jobs", nullable = false)
@OneToMany
private List<JobListing> postedJobs;

@Column(name = "interview_jobs", nullable = false)
@OneToMany
private List<InterviewJob> interviewJobs;
}
