package com.sep.backend.repository;


import com.sep.backend.enums.Gender;
import com.sep.backend.models.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * The interface Candidate repository.
 */
public interface CandidateRepository extends JpaRepository<Candidate, Integer> {

    /**
     * Find first by age and phone number and gender candidate.
     *
     * @param age         the age
     * @param phoneNumber the phone number
     * @param gender      the gender
     * @return the candidate
     */
    Candidate findFirstByAgeAndPhoneNumberAndGender(Integer age, String phoneNumber, Gender gender);
    Candidate findFirstByEmailAddressAndPassword(String email, String password);

    /**
     * Find first by email address candidate.
     *
     * @param emailAddress the email address
     * @return the candidate
     */
    Candidate findFirstByEmailAddress(String emailAddress);

    List<Candidate> findAll();

    void deleteAllByEmailAddress(String emailAddress);

}
