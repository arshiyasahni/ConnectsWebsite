package com.sep.backend.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * The type Build Resume.
 */
@Entity
@Table(name = "build_resume")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BuildResume extends BaseModel {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email_address", unique = true, nullable = false)
    private String emailAddress;

    @Column(name = "linkedIn")
    private String linkedIn;

    @Column(name = "phone_number", unique = true, nullable = false)
    private String phoneNumber;

    @Column(name = "github", unique = true)
    private String github;

    @Column(name = "skills")
    private String skills;

    @Column(name = "exp_org1")
    private String exp_org1;

    @Column(name = "exp_pos1")
    private String exp_pos1;

    @Column(name = "exp_dur1")
    private String exp_dur1;

    @Column(name = "exp_dec1")
    private String exp_dec1;

    @Column(name = "exp_org2")
    private String exp_org2;

    @Column(name = "exp_pos2")
    private String exp_pos2;

    @Column(name = "exp_dur2")
    private String exp_dur2;

    @Column(name = "exp_dec2")
    private String exp_dec2;

    @Column(name = "proj1_title")
    private String proj1_title;

    @Column(name = "proj1_link")
    private String proj1_link;

    @Column(name = "proj1_desc")
    private String proj1_desc;

    @Column(name = "proj2_title")
    private String proj2_title;

    @Column(name = "proj2_link")
    private String proj2_link;

    @Column(name = "proj2_desc")
    private String proj2_desc;

    @Column(name = "edu1_school")
    private String edu1_school;

    @Column(name = "edu1_year")
    private String edu1_year;

    @Column(name = "edu1_degree")
    private String edu1_degree;

    @Column(name = "edu1_dur")
    private String edu1_dur;

    @Column(name = "edu2_school")
    private String edu2_school;

    @Column(name = "edu2_year")
    private String edu2_year;

    @Column(name = "edu2_dur")
    private String edu2_dur;

    @Column(name = "edu2_degree")
    private String edu2_degree;

    @Column(name = "extra_1")
    private String extra_1;

    @Column(name = "extra_2")
    private String extra_2;


}
