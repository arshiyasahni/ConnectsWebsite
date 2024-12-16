package com.sep.backend.service.impl;


import com.sep.backend.models.*;
import com.sep.backend.repository.*;
import com.sep.backend.service.ICandidateJobTrackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The type Candidate Job Track service.
 */
@Service
public class CandidateJobTrackingServiceImpl implements ICandidateJobTrackingService {

    private CandidateJobTrackingRepository candidateJobTrackingRepository;

    private JobApplicationRepository jobApplicationRepository;

    private AcceptedJobRepository acceptedJobRepository;

    private RejectedJobRepository rejectedJobRepository;

    private InterviewJobRepository interviewJobRepository;

    @Autowired
    public CandidateJobTrackingServiceImpl(CandidateJobTrackingRepository candidateJobTrackingRepository, JobApplicationRepository jobApplicationRepository, AcceptedJobRepository acceptedJobRepository, RejectedJobRepository rejectedJobRepository, InterviewJobRepository interviewJobRepository) {
        this.candidateJobTrackingRepository = candidateJobTrackingRepository;
        this.interviewJobRepository = interviewJobRepository;
        this.rejectedJobRepository = rejectedJobRepository;
        this.jobApplicationRepository = jobApplicationRepository;
        this.acceptedJobRepository = acceptedJobRepository;
    }

    @Override
    public CandidateTrackJob findByEmailAddress(String emailAddress)
    {
        List<JobApplication> appliedJobsList = getAppliedJobsByEmail(emailAddress);
        List<AcceptedJob> acceptedJobsList = getAcceptedJobsByEmail(emailAddress);
        List<RejectedJob> rejectedJobsList = getRejectedJobsByEmail(emailAddress);
        List<InterviewJob> interviewJobsList = getInterviewJobsByEmail(emailAddress);
        CandidateTrackJob candidateTrackJob = CandidateTrackJob.builder()
                .emailAddress(emailAddress)
                .acceptedJobs(acceptedJobsList)
                .rejectedJobs(rejectedJobsList)
                .appliedJobs(appliedJobsList)
                .interviewJobs(interviewJobsList)
                .build();

        return candidateTrackJob;
    }

    public List<JobApplication> getAppliedJobsByEmail(String emailAddress) {
        return jobApplicationRepository.findAllByEmailAddress(emailAddress);
    }


    public List<InterviewJob> getInterviewJobsByEmail(String emailAddress) {
        return interviewJobRepository.findAllByEmailAddress(emailAddress);
    }

    public List<AcceptedJob> getAcceptedJobsByEmail(String emailAddress) {
        return acceptedJobRepository.findAllByEmailAddress(emailAddress);
    }

    public List<RejectedJob> getRejectedJobsByEmail(String emailAddress) {
        return rejectedJobRepository.findAllByEmailAddress(emailAddress);
    }

}