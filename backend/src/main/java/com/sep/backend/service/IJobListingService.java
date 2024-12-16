package com.sep.backend.service;

import com.sep.backend.dto.JobListingRequestDto;
import com.sep.backend.exception.JobListingRegistrationException;
import com.sep.backend.models.JobListing;

import java.util.List;


/**
 * The interface Job Listing service.
 */
public interface IJobListingService {

    /**
     * Save job listing.
     *
     * @param jobListingRequestDto the job listing request dto
     * @return the resume
     */
    JobListing saveJobListing(JobListingRequestDto jobListingRequestDto) throws JobListingRegistrationException;

    JobListing getJobListingById(Integer id);
    List<JobListing> getAllJobListings();

    List<JobListing> getAllJobListingsByEmployerEmail(String employerEmail);

    void deleteJobListing(Integer jobId);

}
