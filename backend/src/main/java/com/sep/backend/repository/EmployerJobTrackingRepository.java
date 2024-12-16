package com.sep.backend.repository;


import com.sep.backend.models.CandidateTrackJob;
import com.sep.backend.models.EmployerTrackJob;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * The interface Employer Track Job repository.
 */
public interface EmployerJobTrackingRepository extends JpaRepository<EmployerTrackJob, Integer> {


    /**
     * Find first by email address candidate.
     *
     * @param employerEmail the email address
     * @return the candidate
     */
//    EmployerTrackJob findFirstByEmailAddress(String emailAddress);

    EmployerTrackJob findFirstByEmployerEmail(String employerEmail);

    List<EmployerTrackJob> findAll();

//    void deleteAllByEmailAddress(String emailAddress);

}
