package com.sep.backend.service.impl;


import com.sep.backend.models.*;
import com.sep.backend.repository.*;
import com.sep.backend.service.IAdminJobTrackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The type Candidate Job Track service.
 */
@Service
public class AdminJobTrackingServiceImpl implements IAdminJobTrackingService {

    private AdminJobTrackingRepository adminJobTrackingRepository;

    private JobApplicationRepository jobApplicationRepository;

    private AcceptedJobRepository acceptedJobRepository;

    private RejectedJobRepository rejectedJobRepository;

    private InterviewJobRepository interviewJobRepository;

    @Autowired
    public AdminJobTrackingServiceImpl(AdminJobTrackingRepository adminJobTrackingRepository, JobApplicationRepository jobApplicationRepository, AcceptedJobRepository acceptedJobRepository, RejectedJobRepository rejectedJobRepository, InterviewJobRepository interviewJobRepository) {
        this.adminJobTrackingRepository = adminJobTrackingRepository;
        this.interviewJobRepository = interviewJobRepository;
        this.rejectedJobRepository = rejectedJobRepository;
        this.jobApplicationRepository = jobApplicationRepository;
        this.acceptedJobRepository = acceptedJobRepository;
    }

    @Override
    public AdminTrackJob findAllJobs()
    {
        List<JobApplication> appliedJobsList = getAllAppliedJobs();
        List<AcceptedJob> acceptedJobsList = getAllAcceptedJobs();
        List<RejectedJob> rejectedJobsList = getAllRejectedJobs();
        List<InterviewJob> interviewJobsList = getAllInterviewJobs();
        AdminTrackJob adminTrackJob = AdminTrackJob.builder()
                .acceptedJobs(acceptedJobsList)
                .rejectedJobs(rejectedJobsList)
                .appliedJobs(appliedJobsList)
                .interviewJobs(interviewJobsList)
                .build();

        return adminTrackJob;
    }

    public List<JobApplication> getAllAppliedJobs() {
        return jobApplicationRepository.findAll();
    }


    public List<InterviewJob> getAllInterviewJobs() {
        return interviewJobRepository.findAll();
    }

    public List<AcceptedJob> getAllAcceptedJobs() {
        return acceptedJobRepository.findAll();
    }

    public List<RejectedJob> getAllRejectedJobs() {
        return rejectedJobRepository.findAll();
    }

}