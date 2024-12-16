package com.sep.backend.service;

import com.sep.backend.dto.JobApplicationRequestDto;
import com.sep.backend.exception.JobApplicationRegistrationException;
import com.sep.backend.models.InterviewJob;

/**
 * The interface Interview Job service.
 */
public interface IInterviewJobService {

    /**
     * Save interview job.
     *
     * @param jobApplicationRequestDto the job application request dto
     * @return the resume
     */
    InterviewJob saveInterviewCandidate(JobApplicationRequestDto jobApplicationRequestDto) throws JobApplicationRegistrationException;

    InterviewJob getInterviewJobByEmailAddress(String emailAddress);
}
