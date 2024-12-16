package com.sep.backend.service;


import com.sep.backend.models.EmployerTrackJob;

/**
 * The interface Employer Job Tracking service.
 */
public interface IEmployerJobTrackingService {



EmployerTrackJob findByEmailAddress(String emailAddress);


}
