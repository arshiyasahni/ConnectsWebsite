package com.sep.backend.repository;


import com.sep.backend.models.AcceptedJob;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * The interface Accepted Job repository.
 */
public interface AcceptedJobRepository extends JpaRepository<AcceptedJob, Integer> {


    /**
     * Find first by email address job listing.
     *
     * @param emailAddress the email address
     * @return the job listing
     */
    AcceptedJob findFirstByEmailAddress(String emailAddress);

    List<AcceptedJob> findAll();

    List<AcceptedJob> findAllByEmailAddress(String emailAddress);

    List <AcceptedJob> findAllByEmployerEmail(String emailAddress);

    void deleteAllByEmailAddress(String emailAddress);
}
