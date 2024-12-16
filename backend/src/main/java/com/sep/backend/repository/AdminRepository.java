package com.sep.backend.repository;

import com.sep.backend.models.Admin;
import com.sep.backend.enums.Gender;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * The interface Admin repository.
 */
public interface AdminRepository extends JpaRepository<Admin, Integer> {

    /**
     * Find first by age and phone number and gender Admin.
     *
     * @param age         the age
     * @param phoneNumber the phone number
     * @param gender      the gender
     * @return the admin
     */
    Admin findFirstByAgeAndPhoneNumberAndGender(Integer age, String phoneNumber, Gender gender);

    /**
     * Find first by email address and password admin.
     *
     * @param email    the email
     * @param password the password
     * @return the admin
     */
    Admin findFirstByEmailAddressAndPassword(String email, String password);

    /**
     * Find first by email address admin.
     *
     * @param emailAddress the email address
     * @return the admin
     */
    Admin findFirstByEmailAddress(String emailAddress);

}
