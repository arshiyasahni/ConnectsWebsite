package com.sep.backend.service.impl;


import com.sep.backend.models.*;
import com.sep.backend.repository.*;
import com.sep.backend.service.ICandidateJobTrackingService;
import com.sep.backend.service.IEmployerJobTrackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The type Employer Job Track service.
 */
@Service
public class EmployerJobTrackingServiceImpl implements IEmployerJobTrackingService {

    private EmployerJobTrackingRepository employerJobTrackingRepository;

    private JobListingRepository jobListingRepository;

    private AcceptedJobRepository acceptedJobRepository;

    private RejectedJobRepository rejectedJobRepository;

    private InterviewJobRepository interviewJobRepository;

    @Autowired
    public EmployerJobTrackingServiceImpl(EmployerJobTrackingRepository employerJobTrackingRepository, JobListingRepository jobListingRepository, AcceptedJobRepository acceptedJobRepository, RejectedJobRepository rejectedJobRepository, InterviewJobRepository interviewJobRepository) {
        this.employerJobTrackingRepository = employerJobTrackingRepository;
        this.interviewJobRepository = interviewJobRepository;
        this.rejectedJobRepository = rejectedJobRepository;
        this.jobListingRepository = jobListingRepository;
        this.acceptedJobRepository = acceptedJobRepository;
    }

    @Override
    public EmployerTrackJob findByEmailAddress(String emailAddress)
    {
        List<JobListing> postedJobsList = getPostedJobsByEmail(emailAddress);
        List<AcceptedJob> acceptedJobsList = getAcceptedJobsByEmail(emailAddress);
        List<RejectedJob> rejectedJobsList = getRejectedJobsByEmail(emailAddress);
        List<InterviewJob> interviewJobsList = getInterviewJobsByEmail(emailAddress);
        EmployerTrackJob employerTrackJob = EmployerTrackJob.builder()
                .employerEmail(emailAddress)
                .acceptedJobs(acceptedJobsList)
                .rejectedJobs(rejectedJobsList)
                .postedJobs(postedJobsList)
                .interviewJobs(interviewJobsList)
                .build();

        return employerTrackJob;
    }

    public List<JobListing> getPostedJobsByEmail(String emailAddress) {
        return jobListingRepository.findAllByEmployerEmail(emailAddress);
    }


    public List<InterviewJob> getInterviewJobsByEmail(String emailAddress) {
        return interviewJobRepository.findAllByEmployerEmail(emailAddress);
    }

    public List<AcceptedJob> getAcceptedJobsByEmail(String emailAddress) {
        return acceptedJobRepository.findAllByEmployerEmail(emailAddress);
    }

    public List<RejectedJob> getRejectedJobsByEmail(String emailAddress) {
        return rejectedJobRepository.findAllByEmployerEmail(emailAddress);
    }

}