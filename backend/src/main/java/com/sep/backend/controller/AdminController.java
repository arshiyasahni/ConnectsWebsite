package com.sep.backend.controller;

import com.sep.backend.constants.UriConstants;

import com.sep.backend.dto.AdminJobTrackingResponseDto;
import com.sep.backend.dto.AdminReport;
import com.sep.backend.dto.ResponseDto;
import com.sep.backend.models.AdminTrackJob;
import com.sep.backend.service.IAdminJobTrackingService;
import com.sep.backend.service.IAdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

/**
 * The type Admin controller.
 */
@RestController
@CrossOrigin
@Slf4j
@RequestMapping(UriConstants.BASE_URL)
public class AdminController {

    private final IAdminJobTrackingService iAdminJobTrackingService;

    private final IAdminService iAdminService;

    /**
     * Instantiates a new  controller.
     *
     * @param iAdminJobTrackingService the  service
     */
    @Autowired
    public AdminController(IAdminJobTrackingService iAdminJobTrackingService, IAdminService iAdminService){
        this.iAdminJobTrackingService = iAdminJobTrackingService;
        this.iAdminService = iAdminService;
    }

    @RequestMapping(method = RequestMethod.GET, value = UriConstants.ADMIN_TRACK_JOBS)
    public ResponseDto<AdminJobTrackingResponseDto> adminTrackJobs() {


        AdminTrackJob adminTrackJob;

        adminTrackJob = iAdminJobTrackingService.findAllJobs();
        if(adminTrackJob == null)
        {
            return new ResponseDto<>(Collections.singletonList("No Jobs to track for admin!"));
        }

        // Create the response DTO containing the candidate track data
        AdminJobTrackingResponseDto responseDto = AdminJobTrackingResponseDto.builder()
                .acceptedJobs(adminTrackJob.getAcceptedJobs())
                .appliedJobs(adminTrackJob.getAppliedJobs())
                .interviewJobs(adminTrackJob.getInterviewJobs())
                .rejectedJobs(adminTrackJob.getRejectedJobs())
                .build();

        return new ResponseDto<>(responseDto);

    }

    @RequestMapping(method=RequestMethod.GET, value = UriConstants.ADMIN_REPORT)
    public ResponseDto<AdminReport> getReport(){
        try{
            AdminReport adminReport= iAdminService.getAdminReport();
            return new ResponseDto<>(adminReport);
        }catch (Exception ex){
            log.error("Error occurred ::", ex );
            return new ResponseDto<>(Collections.singletonList("Some Error Occurred"));
        }
    }

}
