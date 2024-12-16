package com.sep.backend.repository;


import com.sep.backend.models.RejectedJob;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * The interface Rejected Job repository.
 */
public interface RejectedJobRepository extends JpaRepository<RejectedJob, Integer> {


    /**
     * Find first by email address job listing.
     *
     * @param emailAddress the email address
     * @return the job listing
     */
    RejectedJob findFirstByEmailAddress(String emailAddress);

    List<RejectedJob> findAll();

    List<RejectedJob> findAllByEmailAddress(String emailAddress);

    List<RejectedJob> findAllByEmployerEmail(String emailAddress);

    void deleteAllByEmailAddress(String emailAddress);

}
