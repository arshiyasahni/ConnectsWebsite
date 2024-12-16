package com.sep.backend.repository;


import com.sep.backend.models.CandidateListing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * The interface CandidateListing repository.
 */
public interface CandidateListingRepository extends JpaRepository<CandidateListing, Integer> {
    List<CandidateListing> findAll();
}
