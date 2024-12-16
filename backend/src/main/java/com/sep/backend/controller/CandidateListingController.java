package com.sep.backend.controller;

import com.sep.backend.constants.UriConstants;
import com.sep.backend.dto.*;
import com.sep.backend.models.*;
import com.sep.backend.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Candidate Listing controller.
 */
@RestController
@CrossOrigin
@Slf4j
@RequestMapping(UriConstants.BASE_URL)
public class CandidateListingController {
    private final ICandidateListingService iCandidateListingService;
    /**
     * Instantiates a new Candidate Listing controller.
     *
     * @param iCandidateListingService the candidate listing service
     */
    @Autowired
    public CandidateListingController(ICandidateListingService iCandidateListingService)
    {
        this.iCandidateListingService = iCandidateListingService;
    }

    /**
     * Get all candidates.
     *
     * @return the list of all candidate
     */
    @RequestMapping(method = RequestMethod.GET, value = UriConstants.GET_ALL_CANDIDATES)
    public List<CandidateListingResponseDto> getAllCandidates() {
        List<CandidateListing> candidateListings = iCandidateListingService.getAllCandidates();
        List<CandidateListingResponseDto> responseDtoList = new ArrayList<>();

        for (CandidateListing candidateListing : candidateListings) {
            CandidateListingResponseDto responseDto = CandidateListingResponseDto.builder()
                    .id(candidateListing.getId())
                    .emailAddress(candidateListing.getEmailAddress())
                    .lastName(candidateListing.getLastName())
                    .phoneNumber(candidateListing.getPhoneNumber())
                    .build();

            responseDtoList.add(responseDto);
        }

        return responseDtoList;
    }

}
