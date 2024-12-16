package com.sep.backend.service.impl;

import com.sep.backend.dto.EmployerRegistrationRequestDto;
import com.sep.backend.dto.LoginResponse;
import com.sep.backend.exception.EmployerRegistrationException;
import com.sep.backend.exception.LoginException;
import com.sep.backend.models.Employer;
import com.sep.backend.models.JobListing;
import com.sep.backend.repository.EmployerRepository;
import com.sep.backend.repository.JobListingRepository;
import com.sep.backend.service.IEmployerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

/**
 * The type employer service.
 */
@Service
public class EmployerServiceImpl extends UserLogin implements IEmployerService {

    private EmployerRepository employerRepository;

    private JobListingRepository jobListingRepository;

    /**
     * Instantiates a new Candidate service.
     *
     * @param employerRepository the employer repository
     */
    @Autowired
    public EmployerServiceImpl(EmployerRepository employerRepository, JobListingRepository jobListingRepository){
        this.employerRepository = employerRepository;
        this.jobListingRepository = jobListingRepository;
    }

    @Override
    public Employer saveEmployer(EmployerRegistrationRequestDto employerRegistrationRequestDto) throws EmployerRegistrationException {

        if (Objects.isNull(employerRegistrationRequestDto.getEmailAddress())) throw new EmployerRegistrationException("Email Address is required");
        else if(Objects.isNull(employerRegistrationRequestDto.getPassword())) throw new EmployerRegistrationException("Password is required");

        if(Objects.nonNull(employerRepository.findFirstByEmailAddress(employerRegistrationRequestDto.getEmailAddress()))){
            throw new EmployerRegistrationException("Employer with same email address already exists");
        }

        Employer employer = Employer.builder()
                .firstName(employerRegistrationRequestDto.getFirstName())
                .lastName(employerRegistrationRequestDto.getLastName())
                .emailAddress(employerRegistrationRequestDto.getEmailAddress())
                .designation(employerRegistrationRequestDto.getDesignation())
                .company_name(employerRegistrationRequestDto.getCompanyName())
                .phoneNumber(employerRegistrationRequestDto.getPhoneNumber())
                .password(employerRegistrationRequestDto.getPassword())
                .registrationNumber(employerRegistrationRequestDto.getRegistrationNumber())
                .build();
        employerRepository.save(employer);
        return employer;
    }

    @Override
    public Employer editEmployer(EmployerRegistrationRequestDto employerRegistrationRequestDto) throws EmployerRegistrationException {

        if (Objects.isNull(employerRegistrationRequestDto.getEmailAddress())) throw new EmployerRegistrationException("Email Address is required");
        else if(Objects.isNull(employerRegistrationRequestDto.getPassword())) throw new EmployerRegistrationException("Password is required");

        Employer existingEmployer = employerRepository.findFirstByEmailAddress(employerRegistrationRequestDto.getEmailAddress());

        if(existingEmployer == null)
        {
            throw new EmployerRegistrationException("Employer not found!");
        }

        if (employerRegistrationRequestDto.getFirstName() != null) {
            existingEmployer.setFirstName(employerRegistrationRequestDto.getFirstName());
        }
        if (employerRegistrationRequestDto.getDesignation() != null) {
            existingEmployer.setDesignation(employerRegistrationRequestDto.getDesignation());
        }
        if (employerRegistrationRequestDto.getLastName() != null) {
            existingEmployer.setLastName(employerRegistrationRequestDto.getLastName());
        }
        if (employerRegistrationRequestDto.getPhoneNumber() != null) {
            existingEmployer.setPhoneNumber(employerRegistrationRequestDto.getPhoneNumber());
        }
        if (employerRegistrationRequestDto.getPassword() != null) {
            existingEmployer.setPassword(employerRegistrationRequestDto.getPassword());
        }

        Employer updatedEmployer = employerRepository.save(existingEmployer);

        return updatedEmployer;
    }
    public LoginResponse getLoginDetails(String email, String password) throws LoginException {
        LoginResponse loginResponse=new LoginResponse();
        Employer employer=employerRepository.findFirstByEmailAddressAndPassword(email, password);
        if(employer==null){
            loginResponse.setLogged(false);
            return loginResponse;
        }
        loginResponse.setId(employer.getId());
        loginResponse.setLogged(true);
        loginResponse.setDesignation(employer.getDesignation());
        loginResponse.setCompanyName(employer.getCompany_name());
        loginResponse.setFirstName(employer.getFirstName());
        loginResponse.setLastName(employer.getLastName());
        loginResponse.setPhoneNumber(employer.getPhoneNumber());
        loginResponse.setEmailAddress(employer.getEmailAddress());
        loginResponse.setRegistrationNumber(employer.getRegistrationNumber());
        return loginResponse;
    }


    @Override
    public Employer getEmployerByEmailAddress(String emailAddress) {

        return employerRepository.findFirstByEmailAddress(emailAddress);
    }


    @Override
    public List<Employer> getEmployerList() {
        return employerRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteEmployer(String emailAddress) {
        jobListingRepository.deleteAllByEmployerEmail(emailAddress);
        employerRepository.deleteAllByEmailAddress(emailAddress);
    }



}
