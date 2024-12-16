package com.sep.backend.models;

import com.sep.backend.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * The type Admin.
 */
@Entity
@Table(name = "admin")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Admin extends BaseModel {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "phone_number", unique = true)
    private String phoneNumber;

    @Column(name = "email_address", unique = true)
    private String emailAddress;

    @Column(name = "gender", nullable = false)
    private Gender gender;

    @Column(name = "age", nullable = false)
    private Integer age;

    @Column(name = "password", nullable = false)
    private String password;

}
