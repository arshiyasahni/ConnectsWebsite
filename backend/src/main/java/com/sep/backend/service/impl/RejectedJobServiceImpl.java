package com.sep.backend.service.impl;


import com.sep.backend.dto.JobApplicationRequestDto;
import com.sep.backend.exception.JobApplicationRegistrationException;
import com.sep.backend.models.RejectedJob;
import com.sep.backend.repository.InterviewJobRepository;
import com.sep.backend.repository.JobApplicationRepository;
import com.sep.backend.repository.RejectedJobRepository;
import com.sep.backend.service.IRejectedJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;

import static com.sep.backend.enums.ApplicationStatus.REJECTED_BY_EMPLOYER;

/**
 * The type Rejected Job service.
 */
@Service
@Transactional
public class RejectedJobServiceImpl implements IRejectedJobService {

    private RejectedJobRepository rejectedJobRepository;

    private JobApplicationRepository jobApplicationRepository;

    private InterviewJobRepository interviewJobRepository;

    /**
     * Instantiates a new Rejected Job service.
     *
     * @param rejectedJobRepository the rejected job repository
     */
    @Autowired
    public RejectedJobServiceImpl(RejectedJobRepository rejectedJobRepository, JobApplicationRepository jobApplicationRepository, InterviewJobRepository interviewJobRepository) {
        this.rejectedJobRepository = rejectedJobRepository;
        this.jobApplicationRepository = jobApplicationRepository;
        this.interviewJobRepository = interviewJobRepository;
    }

    @Override
    public RejectedJob saveRejectCandidate(JobApplicationRequestDto jobApplicationRequestDto) throws JobApplicationRegistrationException {

        if (Objects.isNull(jobApplicationRequestDto.getEmailAddress()))
        {
            throw new JobApplicationRegistrationException("Email Address is required");
        }
        if (Objects.isNull(jobApplicationRequestDto.getJobId()))
        {
            throw new JobApplicationRegistrationException("Job ID is required");
        }

        RejectedJob rejectedJob = RejectedJob.builder()
                .jobID(jobApplicationRequestDto.getJobId())
                .emailAddress(jobApplicationRequestDto.getEmailAddress())
                .employerEmail(jobApplicationRequestDto.getEmployerEmail())
                .applicationStatus(REJECTED_BY_EMPLOYER)
                .build();

        rejectedJobRepository.save(rejectedJob);
        jobApplicationRepository.deleteByJobIDAndEmailAddress(jobApplicationRequestDto.getJobId(),jobApplicationRequestDto.getEmailAddress());
        interviewJobRepository.deleteByJobIDAndEmailAddress(jobApplicationRequestDto.getJobId(), jobApplicationRequestDto.getEmailAddress());
        return rejectedJob;
    }

    @Override
    public RejectedJob getRejectedJobByEmailAddress(String emailAddress)
    {
        return rejectedJobRepository.findFirstByEmailAddress(emailAddress);
    }
}