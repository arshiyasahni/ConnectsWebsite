package com.sep.backend.service;

import com.sep.backend.dto.JobApplicationRequestDto;
import com.sep.backend.exception.JobApplicationRegistrationException;
import com.sep.backend.models.AcceptedJob;

/**
 * The interface Accepted Job service.
 */
public interface IAcceptedJobService {

    /**
     * Save accepted job.
     *
     * @param jobApplicationRequestDto the job application request dto
     * @return the resume
     */
    AcceptedJob saveAcceptCandidate(JobApplicationRequestDto jobApplicationRequestDto) throws JobApplicationRegistrationException;

    AcceptedJob getAcceptedJobByEmailAddress(String emailAddress);

}
