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
 * The type Admin Track Job.
 */
@Entity
@Table(name = "admin_track_job")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminTrackJob extends BaseModel{

@Column(name = "accepted_jobs", nullable = false)
@OneToMany
private List<AcceptedJob> acceptedJobs;

@Column(name = "rejected_jobs", nullable = false)
@OneToMany
private List<RejectedJob> rejectedJobs;

@Column(name = "applied_jobs", nullable = false)
@OneToMany
private List<JobApplication> appliedJobs;

@Column(name = "interview_jobs", nullable = false)
@OneToMany
private List<InterviewJob> interviewJobs;
}
