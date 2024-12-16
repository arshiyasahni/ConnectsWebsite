package com.sep.backend.repository;


import com.sep.backend.models.CandidateTrackJob;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * The interface Candidate Track Job repository.
 */
public interface CandidateJobTrackingRepository extends JpaRepository<CandidateTrackJob, Integer> {


    /**
     * Find first by email address candidate.
     *
     * @param emailAddress the email address
     * @return the candidate
     */
    CandidateTrackJob findFirstByEmailAddress(String emailAddress);


    List<CandidateTrackJob> findAll();

//    void deleteAllByEmailAddress(String emailAddress);

}
