package com.sep.backend.controller;

import com.sep.backend.dto.*;
import com.sep.backend.models.Candidate;
import com.sep.backend.constants.UriConstants;
import com.sep.backend.models.CandidateTrackJob;
import com.sep.backend.service.ICandidateJobTrackingService;
import com.sep.backend.service.ICandidateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sep.backend.exception.CandidateRegistrationException;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;

/**
 * The type Candidate controller.
 */
@RestController
@CrossOrigin
@Slf4j
@RequestMapping(UriConstants.BASE_URL)
public class CandidateController {

     private final ICandidateService iCandidateService;

     private final ICandidateJobTrackingService iCandidateJobTrackingService;

     /**
      * Instantiates a new Candidate controller.
      *
      * @param iCandidateService the candidate service
      */
     @Autowired
     public CandidateController(ICandidateService iCandidateService, ICandidateJobTrackingService iCandidateJobTrackingService){
          this.iCandidateService = iCandidateService;
          this.iCandidateJobTrackingService = iCandidateJobTrackingService;
     }
     /**
      * Register candidate response dto.
      *
      * @param candidateRegistrationRequestDto the candidate registration request dto
      * @return the response dto
      */
     @RequestMapping(method = RequestMethod.POST, value = UriConstants.REGISTER_CANDIDATE)
     public ResponseDto<CandidateRegistrationResponseDto> registerCandidate(@RequestBody CandidateRegistrationRequestDto candidateRegistrationRequestDto){
          Candidate candidate;
          try{
               candidate = iCandidateService.saveCandidate(candidateRegistrationRequestDto);
          } catch (CandidateRegistrationException e) {
               return new ResponseDto<>(Collections.singletonList(e.getMessage()));
          }
          catch (Exception e){
               log.error("Error occurred :: " , e);
               return new ResponseDto<>(Collections.singletonList("Some Error Occurred"));
          }
          return new ResponseDto<>(
                  CandidateRegistrationResponseDto.builder()
                          .id(candidate.getId())
                          .firstName(candidate.getFirstName())
                          .middleName(candidate.getMiddleName())
                          .lastName(candidate.getLastName())
                          .age(candidate.getAge())
                          .emailAddress(candidate.getEmailAddress())
                          .phoneNumber(candidate.getPhoneNumber())
                          .gender(candidate.getGender().getGenderDisplay())
                          .build()
          );
     }

     @RequestMapping(method = RequestMethod.POST, value = UriConstants.EDIT_CANDIDATE)
     public ResponseDto<CandidateRegistrationResponseDto> editCandidate(@RequestBody CandidateRegistrationRequestDto candidateRegistrationRequestDto){
          Candidate candidate;
          try{
               candidate = iCandidateService.editCandidate(candidateRegistrationRequestDto);
          } catch (CandidateRegistrationException e) {
               return new ResponseDto<>(Collections.singletonList(e.getMessage()));
          }
          catch (Exception e){
               log.error("Error occurred :: " , e);
               return new ResponseDto<>(Collections.singletonList("Some Error Occurred"));
          }
          return new ResponseDto<>(
                  CandidateRegistrationResponseDto.builder()
                          .id(candidate.getId())
                          .firstName(candidate.getFirstName())
                          .middleName(candidate.getMiddleName())
                          .lastName(candidate.getLastName())
                          .age(candidate.getAge())
                          .emailAddress(candidate.getEmailAddress())
                          .phoneNumber(candidate.getPhoneNumber())
                          .gender(candidate.getGender().getGenderDisplay())
                          .build()
          );
     }

     @RequestMapping(method = RequestMethod.DELETE, value = UriConstants.DELETE_CANDIDATE)
     private ResponseDto<String> deleteCandidate(@RequestParam(name = "email_address")String emailAddress){
          try{
               iCandidateService.deleteCandidate(emailAddress);
               return new ResponseDto<>("Record Deleted Successfully");
          }
          catch (Exception e){
               log.error("Error occurred :: " , e);
               return new ResponseDto<>(Collections.singletonList("Some Error Occurred"));
          }
     }
     @RequestMapping(method = RequestMethod.POST, value = UriConstants.UPLOAD_RESUME, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
     public ResponseDto<UploadResumeResponse> uploadResume(@RequestParam("email_address") String emailAddress, @RequestParam("uploaded_resume") MultipartFile uploadedResume) {
          Candidate candidate;
          try {
               byte [] resumeData = uploadedResume.getBytes();
               candidate = iCandidateService.uploadResume(emailAddress, resumeData);
          } catch (Exception e) {
               log.error("Error occurred :: ", e);
               return new ResponseDto<>(Collections.singletonList("Some Error Occurred"));
          }
          return new ResponseDto<>(
                  UploadResumeResponse.builder()
                          .emailAddress(candidate.getEmailAddress())
                          .uploadedResume(candidate.getUploadedResume())
                          .build()
          );
     }

     @RequestMapping(method = RequestMethod.GET, value = UriConstants.GET_UPLOADED_RESUME)
     public ResponseEntity<?> getUploadedResume(@RequestParam(name = "email_address") String emailAddress)
     {
          Candidate candidate = iCandidateService.getCandidateByEmailAddress(emailAddress);
          if(candidate == null)
          {
               //custom exception for no such candidate found
          }
          byte [] resumeData = candidate.getUploadedResume();
          return ResponseEntity.status(HttpStatus.OK)
                  .contentType(MediaType.APPLICATION_PDF)
                  .body(resumeData);
     }
     @RequestMapping(method = RequestMethod.GET, value = UriConstants.GET_CANDIDATE)
     public ResponseDto<CandidateRegistrationResponseDto> getCandidateByEmailAddress(@RequestParam (name = "email_address") String emailAddress) {
          Candidate candidate;
          try {
               candidate = iCandidateService.getCandidateByEmailAddress(emailAddress);

               // Check if the candidate exists in the database
               if (candidate == null) {
                    return new ResponseDto<>(Collections.singletonList("Candidate not found"));
               }

               // Create the response DTO containing the candidate data
               CandidateRegistrationResponseDto responseDto = CandidateRegistrationResponseDto.builder()
                       .id(candidate.getId())
                       .firstName(candidate.getFirstName())
                       .middleName(candidate.getMiddleName())
                       .lastName(candidate.getLastName())
                       .age(candidate.getAge())
                       .emailAddress(candidate.getEmailAddress())
                       .phoneNumber(candidate.getPhoneNumber())
                       .gender(candidate.getGender().getGenderDisplay())
                       .build();

               return new ResponseDto<>(responseDto);
          } catch (Exception e) {
               log.error("Error occurred while fetching candidate: ", e);
               return new ResponseDto<>(Collections.singletonList("Some Error Occurred"));
          }
     }

     @RequestMapping(method = RequestMethod.GET, value = UriConstants.CANDIDATE_TRACK_JOBS)
     public ResponseDto<CandidateJobTrackingResponseDto> trackCandidateApplications(@RequestParam (name="email_address") String emailAddress) {


          CandidateTrackJob candidateTrackJob;

          candidateTrackJob = iCandidateJobTrackingService.findByEmailAddress(emailAddress);
          if(candidateTrackJob == null)
          {
               return new ResponseDto<>(Collections.singletonList("Tracked Jobs for Candidate not found"));
          }

          // Create the response DTO containing the candidate track data
          CandidateJobTrackingResponseDto responseDto = CandidateJobTrackingResponseDto.builder()
                  .emailAddress(candidateTrackJob.getEmailAddress())
                  .acceptedJobs(candidateTrackJob.getAcceptedJobs())
                  .appliedJobs(candidateTrackJob.getAppliedJobs())
                  .interviewJobs(candidateTrackJob.getInterviewJobs())
                  .rejectedJobs(candidateTrackJob.getRejectedJobs())
                  .build();

          return new ResponseDto<>(responseDto);

     }
}
