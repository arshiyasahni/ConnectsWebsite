package com.sep.backend.service.impl;


import com.sep.backend.dto.JobApplicationRequestDto;
import com.sep.backend.exception.JobApplicationRegistrationException;
import com.sep.backend.models.AcceptedJob;
import com.sep.backend.repository.AcceptedJobRepository;
import com.sep.backend.repository.InterviewJobRepository;
import com.sep.backend.repository.JobApplicationRepository;
import com.sep.backend.service.IAcceptedJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;

import static com.sep.backend.enums.ApplicationStatus.ACCEPTED_BY_EMPLOYER;

/**
 * The type AcceptedJob service.
 */
@Service
@Transactional
public class AcceptedJobServiceImpl implements IAcceptedJobService {

    private AcceptedJobRepository acceptedJobRepository;

    private JobApplicationRepository jobApplicationRepository;

    private InterviewJobRepository interviewJobRepository;


    /**
     * Instantiates a new Accepted Job service.
     *
     * @param acceptedJobRepository the accepted job repository
     */
    @Autowired
    public AcceptedJobServiceImpl(AcceptedJobRepository acceptedJobRepository, JobApplicationRepository jobApplicationRepository, InterviewJobRepository interviewJobRepository) {
        this.acceptedJobRepository = acceptedJobRepository;
        this.jobApplicationRepository = jobApplicationRepository;
        this.interviewJobRepository = interviewJobRepository;
    }

    @Override
    public AcceptedJob saveAcceptCandidate(JobApplicationRequestDto jobApplicationRequestDto) throws JobApplicationRegistrationException {

        if (Objects.isNull(jobApplicationRequestDto.getEmailAddress()))
        {
            throw new JobApplicationRegistrationException("Email Address is required");
        }
        if (Objects.isNull(jobApplicationRequestDto.getJobId()))
        {
            throw new JobApplicationRegistrationException("Job ID is required");
        }

        AcceptedJob acceptedJob = AcceptedJob.builder()
                .jobID(jobApplicationRequestDto.getJobId())
                .emailAddress(jobApplicationRequestDto.getEmailAddress())
                .employerEmail(jobApplicationRequestDto.getEmployerEmail())
                .applicationStatus(ACCEPTED_BY_EMPLOYER)
                .build();

        acceptedJobRepository.save(acceptedJob);
        jobApplicationRepository.deleteByJobIDAndEmailAddress(jobApplicationRequestDto.getJobId(), jobApplicationRequestDto.getEmailAddress());
        interviewJobRepository.deleteByJobIDAndEmailAddress(jobApplicationRequestDto.getJobId(), jobApplicationRequestDto.getEmailAddress());
        return acceptedJob;
    }

    @Override
    public AcceptedJob getAcceptedJobByEmailAddress(String emailAddress)
    {
        return acceptedJobRepository.findFirstByEmailAddress(emailAddress);
    }
}