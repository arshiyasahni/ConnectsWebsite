package com.sep.backend.repository;


import com.sep.backend.models.InterviewJob;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * The interface Interview Job repository.
 */
public interface InterviewJobRepository extends JpaRepository<InterviewJob, Integer> {


    /**
     * Find first by email address job listing.
     *
     * @param emailAddress the email address
     * @return the job listing
     */
    InterviewJob findFirstByEmailAddress(String emailAddress);

    List<InterviewJob> findAll();

    List<InterviewJob> findAllByEmailAddress(String emailAddress);

    List<InterviewJob> findAllByEmployerEmail(String emailAddress);
    void deleteAllByEmailAddress(String emailAddress);

    void deleteByJobIDAndEmailAddress(Integer jobId, String emailAddress);

}
