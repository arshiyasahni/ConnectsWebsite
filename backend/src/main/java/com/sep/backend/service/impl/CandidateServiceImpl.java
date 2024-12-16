package com.sep.backend.service.impl;

import com.sep.backend.dto.LoginResponse;
import com.sep.backend.enums.Gender;
import com.sep.backend.exception.CandidateRegistrationException;
import com.sep.backend.dto.CandidateRegistrationRequestDto;
import com.sep.backend.exception.LoginException;
import com.sep.backend.models.Candidate;
import com.sep.backend.repository.*;
import com.sep.backend.service.ICandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

/**
 * The type Candidate service.
 */
@Service
public class CandidateServiceImpl extends UserLogin implements ICandidateService {

    private CandidateRepository candidateRepository;

    private JobApplicationRepository jobApplicationRepository;

    private InterviewJobRepository interviewJobRepository;

    private AcceptedJobRepository acceptedJobRepository;

    private RejectedJobRepository rejectedJobRepository;

    /**
     * Instantiates a new Candidate service.
     *
     * @param candidateRepository the Candidate repository
     */
    @Autowired
    public CandidateServiceImpl(AcceptedJobRepository acceptedJobRepository, RejectedJobRepository rejectedJobRepository, JobApplicationRepository jobApplicationRepository, InterviewJobRepository interviewJobRepository,CandidateRepository candidateRepository){
        this.candidateRepository = candidateRepository;
        this.acceptedJobRepository = acceptedJobRepository;
        this.rejectedJobRepository = rejectedJobRepository;
        this.interviewJobRepository = interviewJobRepository;
        this.jobApplicationRepository = jobApplicationRepository;
    }

    @Override
    public Candidate saveCandidate(CandidateRegistrationRequestDto candidateRegistrationRequestDto) throws CandidateRegistrationException {

        if (Objects.isNull(candidateRegistrationRequestDto.getEmailAddress())) throw new CandidateRegistrationException("Email Address is required");
        else if(Objects.isNull(candidateRegistrationRequestDto.getPassword())) throw new CandidateRegistrationException("Password is required");

        if(Objects.nonNull(candidateRepository.findFirstByEmailAddress(candidateRegistrationRequestDto.getEmailAddress()))){
            throw new CandidateRegistrationException("Candidate with same email address already exists");
        }

        Candidate candidate = Candidate.builder()
                .firstName(candidateRegistrationRequestDto.getFirstName())
                .middleName(candidateRegistrationRequestDto.getMiddleName())
                .lastName(candidateRegistrationRequestDto.getLastName())
                .age(candidateRegistrationRequestDto.getAge())
                .emailAddress(candidateRegistrationRequestDto.getEmailAddress())
                .gender(Gender.getGender(candidateRegistrationRequestDto.getGender()))
                .phoneNumber(candidateRegistrationRequestDto.getPhoneNumber())
                .password(candidateRegistrationRequestDto.getPassword())
                .build();
        candidateRepository.save(candidate);
        return candidate;
    }

    public LoginResponse getLoginDetails(String email, String password) throws LoginException {
        LoginResponse loginResponse=new LoginResponse();
        Candidate candidate=candidateRepository.findFirstByEmailAddressAndPassword(email, password);
        if(candidate==null){
            loginResponse.setLogged(false);
            return loginResponse;
        }
        loginResponse.setId(candidate.getId());
        loginResponse.setLogged(true);
        loginResponse.setAge(candidate.getAge());
        loginResponse.setGender(candidate.getGender());
        loginResponse.setFirstName(candidate.getFirstName());
        loginResponse.setMiddleName(candidate.getMiddleName());
        loginResponse.setLastName(candidate.getLastName());
        loginResponse.setPhoneNumber(candidate.getPhoneNumber());
        loginResponse.setEmailAddress(candidate.getEmailAddress());
        return loginResponse;
    }

    @Override
    public Candidate editCandidate(CandidateRegistrationRequestDto candidateRegistrationRequestDto) throws CandidateRegistrationException {

        if (Objects.isNull(candidateRegistrationRequestDto.getEmailAddress())) throw new CandidateRegistrationException("Email Address is required");
        else if(Objects.isNull(candidateRegistrationRequestDto.getPassword())) throw new CandidateRegistrationException("Password is required");

        Candidate existingCandidate = candidateRepository.findFirstByEmailAddress(candidateRegistrationRequestDto.getEmailAddress());

        if(existingCandidate == null)
        {
            throw new CandidateRegistrationException("Candidate not found!");
        }

        if (candidateRegistrationRequestDto.getFirstName() != null) {
            existingCandidate.setFirstName(candidateRegistrationRequestDto.getFirstName());
        }
        if (candidateRegistrationRequestDto.getMiddleName() != null) {
            existingCandidate.setMiddleName(candidateRegistrationRequestDto.getMiddleName());
        }
        if (candidateRegistrationRequestDto.getLastName() != null) {
            existingCandidate.setLastName(candidateRegistrationRequestDto.getLastName());
        }
        if (candidateRegistrationRequestDto.getPhoneNumber() != null) {
            existingCandidate.setPhoneNumber(candidateRegistrationRequestDto.getPhoneNumber());
        }
        if (candidateRegistrationRequestDto.getGender() != null) {
            existingCandidate.setGender(Gender.getGender(candidateRegistrationRequestDto.getGender()));
        }
        if (candidateRegistrationRequestDto.getAge() != null) {
            existingCandidate.setAge(candidateRegistrationRequestDto.getAge());
        }
        if (candidateRegistrationRequestDto.getPassword() != null) {
            existingCandidate.setPassword(candidateRegistrationRequestDto.getPassword());
        }

        Candidate updatedCandidate = candidateRepository.save(existingCandidate);

        return updatedCandidate;
    }
    @Override
    public Candidate uploadResume(String emailAddress, byte[] resumeData) {
        Candidate candidate = candidateRepository.findFirstByEmailAddress(emailAddress);
        candidate.setUploadedResume(resumeData);
        candidateRepository.save(candidate);
        return candidate;
    }
    @Override
    public Candidate getCandidateByEmailAddress(String emailAddress) {
        return candidateRepository.findFirstByEmailAddress(emailAddress);
    }

    @Override
    @Transactional
    public void deleteCandidate(String emailAddress) {
        jobApplicationRepository.deleteAllByEmailAddress(emailAddress);
        acceptedJobRepository.deleteAllByEmailAddress(emailAddress);
        rejectedJobRepository.deleteAllByEmailAddress(emailAddress);
        interviewJobRepository.deleteAllByEmailAddress(emailAddress);
        candidateRepository.deleteAllByEmailAddress(emailAddress);
    }


}
