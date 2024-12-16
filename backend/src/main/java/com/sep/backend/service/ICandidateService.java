package com.sep.backend.service;


import com.sep.backend.dto.CandidateRegistrationRequestDto;
import com.sep.backend.exception.CandidateRegistrationException;
import com.sep.backend.models.Candidate;

import java.util.List;

/**
 * The interface Candidate service.
 */
public interface ICandidateService {

/**
 * Save candidate.
 *
 * @param candidateRegistrationRequestDto the candidate registration request dto
 * @return the candidate
 * @throws CandidateRegistrationException the candidate registration exception
 */
Candidate saveCandidate(CandidateRegistrationRequestDto candidateRegistrationRequestDto) throws CandidateRegistrationException;

Candidate getCandidateByEmailAddress(String emailAddress);



void deleteCandidate(String emailAddress);

Candidate editCandidate(CandidateRegistrationRequestDto candidateRegistrationRequestDto) throws CandidateRegistrationException;

Candidate uploadResume(String emailAddress, byte[] resumeData);

}
