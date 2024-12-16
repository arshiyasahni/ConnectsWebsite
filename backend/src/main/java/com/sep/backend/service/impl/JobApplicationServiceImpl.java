package com.sep.backend.service.impl;


import com.sep.backend.dto.JobApplicationRequestDto;
import com.sep.backend.exception.JobApplicationRegistrationException;
import com.sep.backend.models.JobApplication;
import com.sep.backend.models.JobListing;
import com.sep.backend.repository.JobApplicationRepository;
import com.sep.backend.service.IJobApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Objects;

import static com.sep.backend.enums.ApplicationStatus.*;

/**
 * The type Job Application service.
 */
@Service
public class JobApplicationServiceImpl implements IJobApplicationService {

    private JobApplicationRepository jobApplicationRepository;


    /**
     * Instantiates a new Job Application service.
     *
     * @param jobApplicationRepository the job application repository
     */
    @Autowired
    public JobApplicationServiceImpl(JobApplicationRepository jobApplicationRepository) {
        this.jobApplicationRepository = jobApplicationRepository;

    }

    @Override
    public JobApplication saveJobApplication(JobApplicationRequestDto jobApplicationRequestDto) throws JobApplicationRegistrationException {

        if (Objects.isNull(jobApplicationRequestDto.getEmailAddress()))
        {
            throw new JobApplicationRegistrationException("Email Address is required");
        }
        if (Objects.isNull(jobApplicationRequestDto.getJobId()))
        {
            throw new JobApplicationRegistrationException("Job ID is required");
        }

        JobApplication jobApplication = JobApplication.builder()
                .jobID(jobApplicationRequestDto.getJobId())
                .emailAddress(jobApplicationRequestDto.getEmailAddress())
                .employerEmail(jobApplicationRequestDto.getEmployerEmail())
                .applicationStatus(APPLIED_BY_CANDIDATE)
                .build();

        jobApplicationRepository.save(jobApplication);
        return jobApplication;
    }

    @Override
    public List<JobApplication> getAppliedJobsByJobId(Integer jobId) {
        return jobApplicationRepository.findAllByJobID(jobId);
    }
}