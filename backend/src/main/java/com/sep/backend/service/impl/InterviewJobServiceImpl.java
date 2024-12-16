package com.sep.backend.service.impl;


import com.sep.backend.dto.JobApplicationRequestDto;
import com.sep.backend.exception.JobApplicationRegistrationException;
import com.sep.backend.models.InterviewJob;
import com.sep.backend.repository.InterviewJobRepository;
import com.sep.backend.repository.JobApplicationRepository;
import com.sep.backend.service.IInterviewJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;

import static com.sep.backend.enums.ApplicationStatus.INTERVIEW_WITH_EMPLOYER;

/**
 * The type Interview Job service.
 */
@Service
@Transactional
public class InterviewJobServiceImpl implements IInterviewJobService {

    private InterviewJobRepository interviewJobRepository;

    private JobApplicationRepository jobApplicationRepository;


    /**
     * Instantiates a new Interview Job service.
     *
     * @param interviewJobRepository the interview job repository
     */
    @Autowired
    public InterviewJobServiceImpl(InterviewJobRepository interviewJobRepository, JobApplicationRepository jobApplicationRepository) {
        this.interviewJobRepository = interviewJobRepository;
        this.jobApplicationRepository = jobApplicationRepository;
    }

    @Override
    public InterviewJob saveInterviewCandidate(JobApplicationRequestDto jobApplicationRequestDto) throws JobApplicationRegistrationException {

        if (Objects.isNull(jobApplicationRequestDto.getEmailAddress()))
        {
            throw new JobApplicationRegistrationException("Email Address is required");
        }
        if (Objects.isNull(jobApplicationRequestDto.getJobId()))
        {
            throw new JobApplicationRegistrationException("Job ID is required");
        }

        InterviewJob interviewJob = InterviewJob.builder()
                .jobID(jobApplicationRequestDto.getJobId())
                .emailAddress(jobApplicationRequestDto.getEmailAddress())
                .employerEmail(jobApplicationRequestDto.getEmployerEmail())
                .applicationStatus(INTERVIEW_WITH_EMPLOYER)
                .build();

        interviewJobRepository.save(interviewJob);
        jobApplicationRepository.deleteByJobIDAndEmailAddress(jobApplicationRequestDto.getJobId(), jobApplicationRequestDto.getEmailAddress());
        return interviewJob;
    }

    @Override
    public InterviewJob getInterviewJobByEmailAddress(String emailAddress)
    {
        return interviewJobRepository.findFirstByEmailAddress(emailAddress);
    }
}