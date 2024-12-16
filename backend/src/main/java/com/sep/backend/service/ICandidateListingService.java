package com.sep.backend.service;

import com.sep.backend.models.CandidateListing;

import java.util.List;


/**
 * The interface Candidate Listing service.
 */
public interface ICandidateListingService {

    /**
     * Get all Candidates.
     * @return the candidate list
     */

    List<CandidateListing> getAllCandidates();


}
