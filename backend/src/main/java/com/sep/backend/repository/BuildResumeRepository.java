package com.sep.backend.repository;

import com.sep.backend.models.BuildResume;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * The interface Build Resume repository.
 */
public interface BuildResumeRepository extends JpaRepository<BuildResume, Integer> {



    /**
     * Find first by email address resume.
     *
     * @param emailAddress the email address
     * @return the resume
     */
    BuildResume findFirstByEmailAddress(String emailAddress);

    List<BuildResume> findAll();


}
