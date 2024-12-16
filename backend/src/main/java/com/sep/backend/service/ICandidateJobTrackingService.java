package com.sep.backend.service;


import com.sep.backend.models.CandidateTrackJob;

/**
 * The interface Candidate Job Tracking service.
 */
public interface ICandidateJobTrackingService {



CandidateTrackJob findByEmailAddress(String emailAddress);


}
