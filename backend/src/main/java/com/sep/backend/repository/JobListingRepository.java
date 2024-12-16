package com.sep.backend.repository;


import com.sep.backend.models.JobListing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * The interface JobListing repository.
 */
public interface JobListingRepository extends JpaRepository<JobListing, Integer> {


    /**
     * Find first by email address job listing.
     *
     * @param employerEmail the email address
     * @return the job listing
     */
    JobListing findFirstByEmployerEmail(String employerEmail);

    List<JobListing> findAll();

    List<JobListing> findAllByEmployerEmail(String employerEmail);
    void deleteById(Integer id);

    void deleteAllByEmployerEmail(String emailAddress);

    void deleteAllById(Integer jobId);

}
