package com.sep.backend.service.impl;


import com.sep.backend.dto.JobListingRequestDto;
import com.sep.backend.exception.JobListingRegistrationException;
import com.sep.backend.models.JobListing;
import com.sep.backend.repository.JobApplicationRepository;
import com.sep.backend.repository.JobListingRepository;
import com.sep.backend.service.IJobListingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

/**
 * The type Job Listing service.
 */
@Service
public class JobListingServiceImpl implements IJobListingService {

    private JobListingRepository jobListingRepository;

    private JobApplicationRepository jobApplicationRepository;

    /**
     * Instantiates a new Job Listing service.
     *
     * @param jobListingRepository the job listing repository
     */
    @Autowired
    public JobListingServiceImpl(JobListingRepository jobListingRepository, JobApplicationRepository jobApplicationRepository) {
        this.jobListingRepository = jobListingRepository;
        this.jobApplicationRepository = jobApplicationRepository;
    }

    @Override
    public JobListing saveJobListing(JobListingRequestDto jobListingRequestDto) throws JobListingRegistrationException {

        if (Objects.isNull(jobListingRequestDto.getEmployerEmail())){
            throw new JobListingRegistrationException("Email Address is required");
        }

        JobListing jobListing = JobListing.builder()
                .company(jobListingRequestDto.getCompany())
                .tools(jobListingRequestDto.getTools())
                .salary(jobListingRequestDto.getSalary())
                .languages(jobListingRequestDto.getLanguages())
                .position(jobListingRequestDto.getPosition())
                .role(jobListingRequestDto.getRole())
                .level(jobListingRequestDto.getLevel())
                .salary(jobListingRequestDto.getSalary())
                .postedAt(jobListingRequestDto.getPostedAt())
                .contract(jobListingRequestDto.getContract())
                .location(jobListingRequestDto.getLocation())
                .employerEmail(jobListingRequestDto.getEmployerEmail())
                .description(jobListingRequestDto.getDescription())
                .requirements(jobListingRequestDto.getRequirements()).build();

        jobListingRepository.save(jobListing);
        return jobListing;
    }

    @Override
    public JobListing getJobListingById(Integer id) {
        // Implement the logic to fetch the job listing from the database using the provided ID
        // Return the JobListing entity or null if not found
        return jobListingRepository.findById(id).orElse(null);
    }
    @Override
    public List<JobListing> getAllJobListings() {
        return jobListingRepository.findAll();
    }

    @Override
    public List<JobListing> getAllJobListingsByEmployerEmail(String employerEmail) {
        return jobListingRepository.findAllByEmployerEmail(employerEmail);
    }

    @Override
    @Transactional
    public void deleteJobListing(Integer jobId)
    {
        jobApplicationRepository.deleteAllByJobID(jobId);
        jobListingRepository.deleteById(jobId);
    }
}