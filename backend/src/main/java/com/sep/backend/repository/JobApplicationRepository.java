package com.sep.backend.repository;


import com.sep.backend.models.InterviewJob;
import com.sep.backend.models.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * The interface JobApplication repository.
 */
public interface JobApplicationRepository extends JpaRepository<JobApplication, Integer> {


    /**
     * Find first by email address job listing.
     *
     * @param emailAddress the email address
     * @return the job listing
     */
    JobApplication findFirstByEmailAddress(String emailAddress);

    List<JobApplication> findAll();

    List<JobApplication> findAllByEmailAddress(String emailAddress);

    List<JobApplication> findAllByJobID(Integer jobId);
    void deleteByJobIDAndEmailAddress(Integer jobId, String emailAddress);

    void deleteAllByJobID(Integer jobId);
    void deleteAllByEmailAddress(String emailAddress);

}
