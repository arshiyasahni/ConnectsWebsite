package com.sep.backend.service.impl;


import com.sep.backend.models.CandidateListing;
import com.sep.backend.repository.CandidateListingRepository;
import com.sep.backend.service.ICandidateListingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The type Candidate Listing service.
 */
@Service
public class ICandidateListingServiceImpl implements ICandidateListingService {

    private CandidateListingRepository candidateListingRepository;


    /**
     * Instantiates a new Candidate Listing service.
     *
     * @param candidateListingRepository the candidate listing repository
     */
    @Autowired
    public ICandidateListingServiceImpl(CandidateListingRepository candidateListingRepository) {
        this.candidateListingRepository = candidateListingRepository;

    }

    @Override
    public List<CandidateListing> getAllCandidates() {
        return candidateListingRepository.findAll();
    }

}