package com.sep.backend.controller;

import com.sep.backend.constants.UriConstants;
import com.sep.backend.dto.*;
import com.sep.backend.exception.JobApplicationRegistrationException;
import com.sep.backend.exception.JobListingRegistrationException;
import com.sep.backend.models.*;
import com.sep.backend.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.security.auth.Subject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The type Job Listing controller.
 */
@RestController
@CrossOrigin
@Slf4j
@RequestMapping(UriConstants.BASE_URL)
public class JobListingController {

    private final IJobListingService iJobListingService;
    private final IJobApplicationService iJobApplicationService;

    private final IRejectedJobService iRejectedJobService;

    private final IAcceptedJobService iAcceptedJobService;
    private final IInterviewJobService iInterviewJobService;

    private final IEmailSenderService iEmailSenderService;
    /**
     * Instantiates a new Job Listing controller.
     *
     * @param iJobListingService the job listing service
     */
    @Autowired
    public JobListingController(IEmailSenderService iEmailSenderService, IJobListingService iJobListingService, IJobApplicationService iJobApplicationService, IAcceptedJobService iAcceptedJobService, IRejectedJobService iRejectedJobService, IInterviewJobService iInterviewJobService)
    {
        this.iJobListingService = iJobListingService;
        this.iJobApplicationService = iJobApplicationService;
        this.iRejectedJobService = iRejectedJobService;
        this.iAcceptedJobService = iAcceptedJobService;
        this.iInterviewJobService = iInterviewJobService;
        this.iEmailSenderService = iEmailSenderService;
    }

    /**
     * Register job listing response dto.
     *
     * @param jobListingRequestDto the job listing request dto
     * @return the response dto
     */
    @RequestMapping(method = RequestMethod.POST, value = UriConstants.REGISTER_JOB_LISTING)
    public ResponseDto<JobListingResponseDto> addJobListing(@RequestBody JobListingRequestDto jobListingRequestDto){
        JobListing jobListing;
        try{
            jobListing = iJobListingService.saveJobListing(jobListingRequestDto);
        } catch (JobListingRegistrationException e) {
            return new ResponseDto<>(Collections.singletonList(e.getMessage()));
        }
        catch (Exception e){
            log.error("Error occurred :: " , e);
            return new ResponseDto<>(Collections.singletonList("Some Error Occurred"));
        }
        return new ResponseDto<>(
                JobListingResponseDto.builder()
                        .id(jobListing.getId())
                        .company(jobListing.getCompany())
                        .tools(jobListing.getTools())
                        .salary(jobListing.getSalary())
                        .languages(jobListing.getLanguages())
                        .position(jobListing.getPosition())
                        .role(jobListing.getRole())
                        .level(jobListing.getLevel())
                        .postedAt(jobListing.getPostedAt())
                        .contract(jobListing.getContract())
                        .location(jobListing.getLocation())
                        .employerEmail(jobListing.getEmployerEmail())
                        .description(jobListing.getDescription())
                        .requirements(jobListing.getRequirements())
                        .build()
        );
    }


    /**
     * Get job listing by ID.
     *
     * @param id the ID of the job listing to retrieve
     * @return the response DTO containing the job listing data
     */
    @RequestMapping(method = RequestMethod.GET, value = UriConstants.GET_JOB_LISTING)
    public ResponseDto<JobListingResponseDto> getJobListingById(@RequestParam (name = "id") Integer id) {
        JobListing jobListing;
        try {
            jobListing = iJobListingService.getJobListingById(id);

            // Check if the job listing exists in the database
            if (jobListing == null) {
                return new ResponseDto<>(Collections.singletonList("Job listing not found"));
            }

            // Create the response DTO containing the job listing data
            JobListingResponseDto responseDto = JobListingResponseDto.builder()
                    .id(jobListing.getId())
                    .company(jobListing.getCompany())
                    .tools(jobListing.getTools())
                    .salary(jobListing.getSalary())
                    .languages(jobListing.getLanguages())
                    .position(jobListing.getPosition())
                    .role(jobListing.getRole())
                    .level(jobListing.getLevel())
                    .postedAt(jobListing.getPostedAt())
                    .contract(jobListing.getContract())
                    .location(jobListing.getLocation())
                    .employerEmail(jobListing.getEmployerEmail())
                    .description(jobListing.getDescription())
                    .requirements(jobListing.getRequirements())
                    .build();

            return new ResponseDto<>(responseDto);
        } catch (Exception e) {
            log.error("Error occurred while fetching job listing: ", e);
            return new ResponseDto<>(Collections.singletonList("Some Error Occurred"));
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = UriConstants.DELETE_JOB_LISTING)
    private ResponseDto<String> deleteJobListing(@RequestParam(name = "job_id")Integer jobId){
        try{
            iJobListingService.deleteJobListing(jobId);
            return new ResponseDto<>("Record Deleted Successfully");
        }
        catch (Exception e){
            log.error("Error occurred :: " , e);
            return new ResponseDto<>(Collections.singletonList("Some Error Occurred"));
        }
    }

    /**
     * Get all job listings.
     *
     * @return the list of all job listings
     */
    @RequestMapping(method = RequestMethod.GET, value = UriConstants.GET_JOB_LISTINGS)
    public List<JobListingResponseDto> getAllJobListings() {
        List<JobListing> jobListings = iJobListingService.getAllJobListings();
        List<JobListingResponseDto> responseDtoList = new ArrayList<>();

        for (JobListing jobListing : jobListings) {
            JobListingResponseDto responseDto = JobListingResponseDto.builder()
                    .id(jobListing.getId())
                    .company(jobListing.getCompany())
                    .tools(jobListing.getTools())
                    .salary(jobListing.getSalary())
                    .languages(jobListing.getLanguages())
                    .position(jobListing.getPosition())
                    .role(jobListing.getRole())
                    .level(jobListing.getLevel())
                    .postedAt(jobListing.getPostedAt())
                    .contract(jobListing.getContract())
                    .location(jobListing.getLocation())
                    .employerEmail(jobListing.getEmployerEmail())
                    .description(jobListing.getDescription())
                    .requirements(jobListing.getRequirements())
                    .build();

            responseDtoList.add(responseDto);
        }

        return responseDtoList;
    }

    @RequestMapping(method = RequestMethod.GET, value = UriConstants.EMPLOYER_GET_JOB_LISTINGS)
    public List<JobListingResponseDto> getAllJobListingsByEmployerEmail(@RequestParam (name = "employer_email") String employerEmail) {
        List<JobListing> jobListings = iJobListingService.getAllJobListingsByEmployerEmail(employerEmail);
        List<JobListingResponseDto> responseDtoList = new ArrayList<>();

        for (JobListing jobListing : jobListings) {
            JobListingResponseDto responseDto = JobListingResponseDto.builder()
                    .id(jobListing.getId())
                    .company(jobListing.getCompany())
                    .tools(jobListing.getTools())
                    .salary(jobListing.getSalary())
                    .languages(jobListing.getLanguages())
                    .position(jobListing.getPosition())
                    .role(jobListing.getRole())
                    .level(jobListing.getLevel())
                    .postedAt(jobListing.getPostedAt())
                    .contract(jobListing.getContract())
                    .location(jobListing.getLocation())
                    .employerEmail(jobListing.getEmployerEmail())
                    .description(jobListing.getDescription())
                    .requirements(jobListing.getRequirements())
                    .build();

            responseDtoList.add(responseDto);
        }

        return responseDtoList;
    }

    @RequestMapping(method = RequestMethod.POST, value = UriConstants.CANDIDATE_APPLY_JOB)
    public ResponseDto<JobApplicationResponseDto> applyForJob(@RequestBody JobApplicationRequestDto jobApplicationRequestDto){
        JobApplication jobApplication;
        try{
            jobApplication = iJobApplicationService.saveJobApplication(jobApplicationRequestDto);
            sendEmailToEmployer(jobApplication);
        } catch (JobApplicationRegistrationException e) {
            return new ResponseDto<>(Collections.singletonList(e.getMessage()));
        }
        catch (Exception e){
            log.error("Error occurred :: " , e);
            return new ResponseDto<>(Collections.singletonList("Some Error Occurred"));
        }
        return new ResponseDto<>(
                JobApplicationResponseDto.builder()
                        .jobId(jobApplication.getJobID())
                        .emailAddress(jobApplication.getEmailAddress())
                        .employerEmail(jobApplication.getEmployerEmail())
                        .applicationStatus(String.valueOf(jobApplication.getApplicationStatus()))
                        .build()
        );
    }

    @RequestMapping(method = RequestMethod.GET, value = UriConstants.GET_CANDIDATES_APPLY_JOB)
    public List<JobApplicationResponseDto> getAppliedCandidatesByEmailAddress(@RequestParam (name = "job_id") Integer jobId){
        List<JobApplication> jobApplications = iJobApplicationService.getAppliedJobsByJobId(jobId);
        List<JobApplicationResponseDto> responseDtoList = new ArrayList<>();

        for (JobApplication jobApplication : jobApplications) {
            JobApplicationResponseDto responseDto = JobApplicationResponseDto.builder()
                    .jobId(jobApplication.getJobID())
                    .emailAddress(jobApplication.getEmailAddress())
                    .employerEmail(jobApplication.getEmployerEmail())
                    .applicationStatus(String.valueOf(jobApplication.getApplicationStatus()))
                    .build();

            responseDtoList.add(responseDto);
        }

        return responseDtoList;
    }

    @RequestMapping(method = RequestMethod.POST, value = UriConstants.EMPLOYER_REJECT_CANDIDATE)
    public ResponseDto<JobApplicationResponseDto> employerRejectCandidate(@RequestBody JobApplicationRequestDto jobApplicationRequestDto){
        RejectedJob rejectedJob;
        try{
            rejectedJob = iRejectedJobService.saveRejectCandidate(jobApplicationRequestDto);
            sendEmailToCandidate(rejectedJob);
        } catch (JobApplicationRegistrationException e) {
            return new ResponseDto<>(Collections.singletonList(e.getMessage()));
        }
        catch (Exception e){
            log.error("Error occurred :: " , e);
            return new ResponseDto<>(Collections.singletonList("Some Error Occurred"));
        }
        return new ResponseDto<>(
                JobApplicationResponseDto.builder()
                        .jobId(rejectedJob.getJobID())
                        .emailAddress(rejectedJob.getEmailAddress())
                        .employerEmail(rejectedJob.getEmployerEmail())
                        .applicationStatus(String.valueOf(rejectedJob.getApplicationStatus()))
                        .build()
        );
    }

    @RequestMapping(method = RequestMethod.POST, value = UriConstants.EMPLOYER_ACCEPT_CANDIDATE)
    public ResponseDto<JobApplicationResponseDto> employerAcceptCandidate(@RequestBody JobApplicationRequestDto jobApplicationRequestDto){
        AcceptedJob acceptedJob;
        try{
            acceptedJob = iAcceptedJobService.saveAcceptCandidate(jobApplicationRequestDto);
            sendEmailToCandidate(acceptedJob);
        } catch (JobApplicationRegistrationException e) {
            return new ResponseDto<>(Collections.singletonList(e.getMessage()));
        }
        catch (Exception e){
            log.error("Error occurred :: " , e);
            return new ResponseDto<>(Collections.singletonList("Some Error Occurred"));
        }
        return new ResponseDto<>(
                JobApplicationResponseDto.builder()
                        .jobId(acceptedJob.getJobID())
                        .emailAddress(acceptedJob.getEmailAddress())
                        .employerEmail(acceptedJob.getEmployerEmail())
                        .applicationStatus(String.valueOf(acceptedJob.getApplicationStatus()))
                        .build()
        );
    }

    @RequestMapping(method = RequestMethod.POST, value = UriConstants.EMPLOYER_SET_INTERVIEW)
    public ResponseDto<JobApplicationResponseDto> employerSetInterview(@RequestBody JobApplicationRequestDto jobApplicationRequestDto){
        InterviewJob interviewJob;
        try{
            interviewJob = iInterviewJobService.saveInterviewCandidate(jobApplicationRequestDto);
            sendEmailToCandidate(interviewJob);
        } catch (JobApplicationRegistrationException e) {
            return new ResponseDto<>(Collections.singletonList(e.getMessage()));
        }
        catch (Exception e){
            log.error("Error occurred :: " , e);
            return new ResponseDto<>(Collections.singletonList("Some Error Occurred"));
        }
        return new ResponseDto<>(
                JobApplicationResponseDto.builder()
                        .jobId(interviewJob.getJobID())
                        .emailAddress(interviewJob.getEmailAddress())
                        .employerEmail(interviewJob.getEmployerEmail())
                        .applicationStatus(String.valueOf(interviewJob.getApplicationStatus()))
                        .build()
        );
    }

    private void sendEmailToEmployer(JobApplication jobApplication)
    {
        String subject = "Job Application Notification for Job ID: "+jobApplication.getJobID();
        String messageContent = "Dear Employer," + "\n\n"
                + "We hope this email finds you well. We wanted to inform you that a new job application has been submitted for the job posting "
                + "referenced by the Job ID: " + jobApplication.getJobID() + ".\n\n"
                + "The candidate's qualifications, skills, and experience have been carefully reviewed, and we believe they could be a valuable "
                + "addition to your team. We encourage you to take a closer look at their application.\n\n"
                + "You can view the candidate's full application and profile on our platform.\n\n"
                + "If you have any questions or would like to proceed with the application, please log in to your account and navigate to the "
                + "job posting for more details. Feel free to contact the candidate directly through the provided email address.\n\n"
                + "Thank you for using Connects. Career Service Platform.\n\n"
                + "Best regards,\n" + "Connects Team\n";


        this.iEmailSenderService.sendEmail(jobApplication.getEmployerEmail(),subject, messageContent);

    }

    private void sendEmailToCandidate(RejectedJob rejectedJob)
    {
        String subject = "Job Application Notification for Job ID: "+rejectedJob.getJobID();
        String messageContent = "Dear Candidate" + ",\n\n"
                + "We hope this email finds you well. We wanted to reach out and share some important information regarding your recent job application. "
                + "After careful consideration, we regret to inform you that your application for the Job referenced by the Job ID: " + rejectedJob.getJobID() + " has been rejected.\n\n"
                + "While your qualifications and skills are impressive, the selection process was highly competitive, and the company had to make some difficult decisions.\n\n"
                + "We appreciate your interest in the position and your enthusiasm for joining their team. Your efforts and dedication did not go unnoticed, "
                + "and we encourage you to continue pursuing your career aspirations.\n\n"
                + "We understand that receiving news of rejection can be disappointing, but please remember that this outcome doesn't define your abilities "
                + "and potential. Many factors contribute to the selection process, and we hope you will consider applying for future opportunities with them.\n\n"
                + "If you have any questions or would like feedback on your application, please don't hesitate to reach out to the employer at " + rejectedJob.getEmployerEmail() + ". "
                + "We'd be happy to provide you with constructive feedback to assist you in your job search.\n\n"
                + "Thank you for considering us as part of your journey, and we wish you the very best in your future endeavors.\n\n"
                + "We encourage you to keep exploring opportunities on Connects. Career Service Platform and leverage the resources available to enhance your "
                + "professional growth.\n\n"
                + "Best regards,\n" + "Connects Team";

        this.iEmailSenderService.sendEmail(rejectedJob.getEmailAddress(),subject, messageContent);
    }

    private void sendEmailToCandidate(AcceptedJob acceptedJob)
    {
        String subject = "Job Application Notification for Job ID: "+acceptedJob.getJobID();
        String messageContent = "Dear Candidate" + ",\n\n"
                + "We hope this email finds you well. We wanted to reach out and share some great news with you. "
                + "After careful consideration, we are pleased to inform you that your application for the Job referenced by the Job ID: " + acceptedJob.getJobID() + " has been accepted!\n\n"
                + "The employers were impressed by your qualifications, skills, and experience, and believed that you will be a valuable "
                + "addition to their team. "
                + "We are excited to have you join this company in achieving their goals.\n\n"
                + "Here are the next steps in the process:\n\n"
                + "1. An official offer letter will be sent to you that outlines the terms of your employment, including compensation, "
                + "benefits, and start date.\n\n"
                + "2. Please review the offer letter carefully and let the employers know if you have any questions or concerns. You can reach out "
                + "to them at " + acceptedJob.getEmployerEmail() + "\n\n"
                + "3. Once you are ready, please sign and return the offer letter within 3 weeks of receiving this email to confirm your "
                + "acceptance of the position.\n\n"
                + "4. Their HR team will also provide you with information about the onboarding process, including any paperwork and "
                + "training sessions you need to complete.\n\n"
                + "We are excited for all the opportunities you will be getting while working with such an elite company and we look forward to you choosing us to serve you in the future. If you have "
                + "any immediate questions or need further information, please don't hesitate to reach out to the employer directly at " + acceptedJob.getEmployerEmail() + ".\n\n"
                + "Once again, congratulations on your acceptance, and we wish you the very best.\n\n"
                +"Thank you for using Connects. Career Service Platform.\n\n"
                + "Best regards,\n" + "Connects Team\n";

        this.iEmailSenderService.sendEmail(acceptedJob.getEmailAddress(),subject,messageContent);
    }

    private void sendEmailToCandidate(InterviewJob interviewJob)
    {
        String subject = "Job Application Notification for Job ID: "+interviewJob.getJobID();
        String messageContent = "Dear Candidate," + "\n\n"
                + "We hope this email finds you well. We wanted to reach out and share some great news with you. "
                + "After careful consideration, we are pleased to inform you that your application for the Job referenced by the Job ID: " + interviewJob.getJobID() + " has been shortlisted for an interview!\n\n"
                + "The employers were impressed by your qualifications, skills, and experience, and believe that you will be a strong "
                + "candidate for the position. Congratulations on making it to this stage of the selection process.\n\n"
                + "Here are the next steps in the process:\n\n"
                + "1. An official invitation for the interview will be sent to you, including details about the date, time, and location of the interview.\n\n"
                + "2. Please make sure to review the interview details carefully and prepare for the interview accordingly. If you have any questions or need clarification, you can reach out "
                + "to the employers at " + interviewJob.getEmployerEmail() + "\n\n"
                + "3. On the scheduled date, be sure to arrive at the interview location on time and present yourself professionally.\n\n"
                + "4. After the interview, the employers will provide you with feedback and further instructions regarding the next steps of the process.\n\n"
                + "We are excited about the opportunity for you to showcase your skills and potential during the interview. If you have "
                + "any immediate questions or need further information, please don't hesitate to reach out to the employer directly at " + interviewJob.getEmployerEmail() + ".\n\n"
                + "Once again, congratulations on being shortlisted for the interview, and we wish you the very best.\n\n"
                + "Thank you for using Connects. Career Service Platform.\n\n"
                + "Best regards,\n" + "Connects Team";


        this.iEmailSenderService.sendEmail(interviewJob.getEmailAddress(),subject,messageContent);
    }


}
