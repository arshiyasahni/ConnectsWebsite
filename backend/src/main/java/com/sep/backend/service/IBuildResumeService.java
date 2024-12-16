package com.sep.backend.service;

import com.sep.backend.dto.BuildResumeRequestDto;
import com.sep.backend.exception.BuildResumeException;
import com.sep.backend.models.BuildResume;


/**
 * The interface Build Resume service.
 */
public interface IBuildResumeService {

    /**
     * Save resume.
     *
     * @param buildResumeRequestdto the build resume request dto
     * @return the resume
     */
    BuildResume saveResume(BuildResumeRequestDto buildResumeRequestdto) throws BuildResumeException;

    BuildResume getResumeByEmailAddress(String emailAddress);

}
