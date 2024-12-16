package com.sep.backend.service;

import com.sep.backend.dto.EmployerRegistrationRequestDto;
import com.sep.backend.exception.EmployerRegistrationException;
import com.sep.backend.models.Employer;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The interface Employer service.
 */
@Service
public interface IEmployerService {

    /**
     * Save Employer .
     *
     * @param employerRegistrationRequestDto the employer registration request dto
     * @return the employer
     * @throws EmployerRegistrationException the employer registration exception
     */
    Employer saveEmployer(EmployerRegistrationRequestDto employerRegistrationRequestDto) throws EmployerRegistrationException;

    Employer editEmployer(EmployerRegistrationRequestDto employerRegistrationRequestDto) throws EmployerRegistrationException;
    List<Employer> getEmployerList();

    Employer getEmployerByEmailAddress(String emailAddress);

    void deleteEmployer(String emailAddress);

}
