package com.sep.backend.service.impl;

import com.sep.backend.dto.AdminReport;
import com.sep.backend.dto.JobsCount;
import com.sep.backend.dto.LoginResponse;

import com.sep.backend.dto.UsersCount;
import com.sep.backend.exception.LoginException;

import com.sep.backend.models.Admin;

import com.sep.backend.models.JobListing;
import com.sep.backend.repository.*;
import com.sep.backend.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type Admin service.
 */
@Service
public class AdminServiceImpl extends  UserLogin implements IAdminService {

    private AdminRepository adminRepository;

    private EmployerRepository employerRepository;

    private CandidateRepository candidateRepository;

    private JobApplicationRepository jobApplicationRepository;

    private JobListingRepository jobListingRepository;

    private AcceptedJobRepository acceptedJobRepository; // for inactive posts

    private InterviewJobRepository interviewJobRepository;
    /**
     * Instantiates a new Admin service.
     *
     * @param adminRepository the admin repository
     */
    @Autowired
    public AdminServiceImpl(AdminRepository adminRepository, EmployerRepository employerRepository, CandidateRepository candidateRepository, JobApplicationRepository jobApplicationRepository, JobListingRepository jobListingRepository, AcceptedJobRepository acceptedJobRepository, InterviewJobRepository interviewJobRepository){
        this.adminRepository = adminRepository;
        this.employerRepository = employerRepository;
        this.candidateRepository = candidateRepository;
        this.jobApplicationRepository = jobApplicationRepository;
        this.jobListingRepository = jobListingRepository;
        this.acceptedJobRepository = acceptedJobRepository;
        this.interviewJobRepository = interviewJobRepository;
    }


    public  LoginResponse getLoginDetails(String email, String password) throws LoginException {
        LoginResponse loginResponse=new LoginResponse();
        Admin admin = adminRepository.findFirstByEmailAddressAndPassword(email, password);
        if(admin ==null){
            loginResponse.setLogged(false);
            return loginResponse;
        }
        loginResponse.setLogged(true);
        loginResponse.setAge(admin.getAge());
        loginResponse.setGender(admin.getGender());
        loginResponse.setFirstName(admin.getFirstName());
        loginResponse.setMiddleName(admin.getMiddleName());
        loginResponse.setLastName(admin.getLastName());
        loginResponse.setPhoneNumber(admin.getPhoneNumber());
        loginResponse.setEmailAddress(admin.getEmailAddress());
        return loginResponse;
    }

    @Override
    public AdminReport getAdminReport() {
        int employerCount=employerRepository.findAll().size();
        int candidateCount=candidateRepository.findAll().size();
        UsersCount usersCount=UsersCount.builder()
                .employerCount(employerCount)
                .candidateCount(candidateCount)
                .build();
        int postedJobs=jobListingRepository.findAll().size();
        int inactiveJobs=acceptedJobRepository.findAll().size();
        int activeJobs=jobApplicationRepository.findAll().size() + interviewJobRepository.findAll().size();
        JobsCount jobsCount = JobsCount.builder()
                .activeJobs(activeJobs)
                .inactiveJobs(inactiveJobs)
                .postedJobs(postedJobs)
                .build();
        return AdminReport.builder()
                .jobsCount(jobsCount)
                .usersCount(usersCount)
                .build();
    }

}
