package com.sep.backend.controller;

import com.sep.backend.constants.UriConstants;
import com.sep.backend.dto.*;
import com.sep.backend.exception.EmployerRegistrationException;
import com.sep.backend.models.CandidateTrackJob;
import com.sep.backend.models.Employer;
import com.sep.backend.models.EmployerTrackJob;
import com.sep.backend.models.JobListing;
import com.sep.backend.service.IEmployerJobTrackingService;
import com.sep.backend.service.IEmployerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

/**
 * The type Employer controller.
 */
@RestController
@CrossOrigin
@Slf4j
@RequestMapping(UriConstants.BASE_URL)
public class EmployerController {
    private final IEmployerService iEmployerService;

    private final IEmployerJobTrackingService iEmployerJobTrackingService;
    /**
     * Instantiates a new  controller.
     *
     * @param iEmployerService the  service
     */

    @Autowired
    public EmployerController(IEmployerService iEmployerService, IEmployerJobTrackingService iEmployerJobTrackingService){
        this.iEmployerService = iEmployerService;
        this.iEmployerJobTrackingService = iEmployerJobTrackingService;
    }
    /**
     * Register employer response dto.
     *
     * @param employerRegistrationRequestDto the employer registration request dto
     * @return the response dto
     */
    @RequestMapping(method = RequestMethod.POST, value = UriConstants.REGISTER_EMPLOYER)
    public ResponseDto<EmployerRegistrationResponseDto> registerEmployer(@RequestBody EmployerRegistrationRequestDto employerRegistrationRequestDto){
        Employer employer;
        try{
            employer = iEmployerService.saveEmployer(employerRegistrationRequestDto);
        } catch (EmployerRegistrationException e) {
            return new ResponseDto<>(Collections.singletonList(e.getMessage()));
        }
        catch (Exception e){
            log.error("Error occurred ::", e );
            return new ResponseDto<>(Collections.singletonList("Some Error Occurred"));
        }
        return new ResponseDto<>(
                EmployerRegistrationResponseDto.builder()
                        .id(employer.getId())
                        .firstName(employer.getFirstName())

                        .lastName(employer.getLastName())

                        .emailAddress(employer.getEmailAddress())
                        .phoneNumber(employer.getPhoneNumber())

                        .registrationNumber(employer.getRegistrationNumber())
                        .designation(employer.getDesignation())
                        .companyName(employer.getCompany_name())
                        .build()
        );
    }

    @RequestMapping(method = RequestMethod.POST, value = UriConstants.EDIT_EMPLOYER)
    public ResponseDto<EmployerRegistrationResponseDto> editEmployer(@RequestBody EmployerRegistrationRequestDto employerRegistrationRequestDto){
        Employer employer;
        try{
            employer = iEmployerService.editEmployer(employerRegistrationRequestDto);
        } catch (EmployerRegistrationException e) {
            return new ResponseDto<>(Collections.singletonList(e.getMessage()));
        }
        catch (Exception e){
            log.error("Error occurred ::", e );
            return new ResponseDto<>(Collections.singletonList("Some Error Occurred"));
        }
        return new ResponseDto<>(
                EmployerRegistrationResponseDto.builder()
                        .id(employer.getId())
                        .firstName(employer.getFirstName())
                        .lastName(employer.getLastName())
                        .emailAddress(employer.getEmailAddress())
                        .phoneNumber(employer.getPhoneNumber())
                        .registrationNumber(employer.getRegistrationNumber())
                        .designation(employer.getDesignation())
                        .companyName(employer.getCompany_name())
                        .build()
        );
    }

    @RequestMapping(method = RequestMethod.GET, value = UriConstants.GET_EMPLOYER_LIST)
    public ResponseDto<List<Employer>> getEmployersList(){

        List<Employer> list;
        try{
            list = iEmployerService.getEmployerList();
        }
        catch (Exception e){
            log.error("Error occurred :: " , e);
            return new ResponseDto<>(Collections.singletonList("Some Error Occurred"));
        }
        return new ResponseDto<>(list);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = UriConstants.DELETE_EMPLOYER)
    private ResponseDto<String> deleteEmployer(@RequestParam(name = "employer_email")String emailAddress){
        try{
            iEmployerService.deleteEmployer(emailAddress);
            return new ResponseDto<>("Record Deleted Successfully");
        }
        catch (Exception e){
            log.error("Error occurred :: " , e);
            return new ResponseDto<>(Collections.singletonList("Some Error Occurred"));
        }
    }

    /**
     * Get employer by ID.
     *
     * @param emailAddress the ID of the employer to retrieve
     * @return the response DTO containing the employer data
     */
    @RequestMapping(method = RequestMethod.GET, value = UriConstants.GET_EMPLOYER)
    public ResponseDto<EmployerRegistrationResponseDto> getEmployerByEmployerEmail(@RequestParam (name = "employer_email") String emailAddress) {
        Employer employer;
        try {
            employer = iEmployerService.getEmployerByEmailAddress(emailAddress);

            // Check if the employer exists in the database
            if (employer == null) {
                return new ResponseDto<>(Collections.singletonList("Employer not found"));
            }

            // Create the response DTO containing the job listing data
            EmployerRegistrationResponseDto responseDto = EmployerRegistrationResponseDto.builder()
                    .id(employer.getId())
                    .firstName(employer.getFirstName())
                    .lastName(employer.getLastName())
                    .emailAddress(employer.getEmailAddress())
                    .phoneNumber(employer.getPhoneNumber())
                    .registrationNumber(employer.getRegistrationNumber())
                    .designation(employer.getDesignation())
                    .companyName(employer.getCompany_name())
                    .build();

            return new ResponseDto<>(responseDto);
        } catch (Exception e) {
            log.error("Error occurred while fetching employer: ", e);
            return new ResponseDto<>(Collections.singletonList("Some Error Occurred"));
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = UriConstants.EMPLOYER_TRACK_JOBS)
    public ResponseDto<EmployerJobTrackingResponseDto> trackEmployerApplications(@RequestParam (name="employer_email") String emailAddress) {


        EmployerTrackJob employerTrackJob;

        employerTrackJob = iEmployerJobTrackingService.findByEmailAddress(emailAddress);
        if(employerTrackJob == null)
        {
            return new ResponseDto<>(Collections.singletonList("Tracked Jobs for Employer not found"));
        }

        // Create the response DTO containing the candidate track data
        EmployerJobTrackingResponseDto responseDto = EmployerJobTrackingResponseDto.builder()
                .employerEmail(employerTrackJob.getEmployerEmail())
                .acceptedJobs(employerTrackJob.getAcceptedJobs())
                .postedJobs(employerTrackJob.getPostedJobs())
                .interviewJobs(employerTrackJob.getInterviewJobs())
                .rejectedJobs(employerTrackJob.getRejectedJobs())
                .build();

        return new ResponseDto<>(responseDto);

    }

}