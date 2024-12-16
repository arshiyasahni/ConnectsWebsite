package com.sep.backend.controller;

import com.sep.backend.constants.UriConstants;
import com.sep.backend.dto.BuildResumeRequestDto;
import com.sep.backend.dto.BuildResumeResponseDto;
import com.sep.backend.dto.JobListingResponseDto;
import com.sep.backend.dto.ResponseDto;
import com.sep.backend.exception.BuildResumeException;
import com.sep.backend.models.BuildResume;
import com.sep.backend.models.JobListing;
import com.sep.backend.service.IBuildResumeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

/**
 * The type Build Resume controller.
 */
@RestController
@CrossOrigin
@Slf4j
@RequestMapping(UriConstants.BASE_URL)
public class BuildResumeController {

    private final IBuildResumeService iBuildResumeService;

    /**
     * Instantiates a new Build Resume controller.
     *
     * @param iBuildResumeService the build resume service
     */
    @Autowired
    public BuildResumeController(IBuildResumeService iBuildResumeService){
        this.iBuildResumeService = iBuildResumeService;
    }

    /**
     * Register build resume response dto.
     *
     * @param buildResumeRequestDto the build resume request dto
     * @return the response dto
     */
    @RequestMapping(method = RequestMethod.POST, value = UriConstants.BUILD_RESUME)
    public ResponseDto<BuildResumeResponseDto> buildResume(@RequestBody BuildResumeRequestDto buildResumeRequestDto){
        BuildResume buildResume;
        try{
            buildResume = iBuildResumeService.saveResume(buildResumeRequestDto);
        } catch (BuildResumeException e) {
            return new ResponseDto<>(Collections.singletonList(e.getMessage()));
        }
        catch (Exception e){
            log.error("Error occurred :: " , e);
            return new ResponseDto<>(Collections.singletonList("Some Error Occurred"));
        }
        return new ResponseDto<>(
                BuildResumeResponseDto.builder()
                        .name(buildResume.getName())
                        .emailAddress(buildResume.getEmailAddress())
                        .phoneNumber(buildResume.getPhoneNumber())
                        .skills(buildResume.getSkills())
                        .github(buildResume.getGithub())
                        .linkedIn(buildResume.getLinkedIn())
                        .exp_org1(buildResume.getExp_org1())
                        .exp_pos1(buildResume.getExp_pos1())
                        .exp_dur1(buildResume.getExp_dur1())
                        .exp_dec1(buildResume.getExp_dec1())
                        .edu1_school(buildResume.getEdu1_school())
                        .edu1_degree(buildResume.getEdu1_degree())
                        .edu1_year(buildResume.getEdu1_year())
                        .edu1_dur(buildResume.getEdu1_dur())
                        .proj1_title(buildResume.getProj1_title())
                        .proj1_desc(buildResume.getProj1_desc())
                        .proj1_link(buildResume.getProj1_link())
                        .extra_1(buildResume.getExtra_1())
                        .exp_org2(buildResume.getExp_org2())
                        .exp_pos2(buildResume.getExp_pos2())
                        .exp_dur2(buildResume.getExp_dur2())
                        .exp_dec2(buildResume.getExp_dec2())
                        .edu2_school(buildResume.getEdu2_school())
                        .edu2_degree(buildResume.getEdu2_degree())
                        .edu2_year(buildResume.getEdu2_year())
                        .edu2_dur(buildResume.getEdu2_dur())
                        .proj2_title(buildResume.getProj2_title())
                        .proj2_desc(buildResume.getProj2_desc())
                        .proj2_link(buildResume.getProj2_link())
                        .extra_2(buildResume.getExtra_2())
                        .build()
        );
    }

    @RequestMapping(method = RequestMethod.GET, value = UriConstants.GET_BUILT_RESUME)
    public ResponseDto<BuildResumeResponseDto> getBuiltResume(@RequestParam (name = "email_address") String emailAddress) {
        BuildResume buildResume;
        try {
            buildResume = iBuildResumeService.getResumeByEmailAddress(emailAddress);

            // Check if the built resume exists in the database
            if (buildResume == null) {
                return new ResponseDto<>(Collections.singletonList("No such resume found"));
            }

            // Create the response DTO containing the built resume data
            BuildResumeResponseDto responseDto = BuildResumeResponseDto.builder()
                    .name(buildResume.getName())
                    .emailAddress(buildResume.getEmailAddress())
                    .phoneNumber(buildResume.getPhoneNumber())
                    .skills(buildResume.getSkills())
                    .github(buildResume.getGithub())
                    .linkedIn(buildResume.getLinkedIn())
                    .exp_org1(buildResume.getExp_org1())
                    .exp_pos1(buildResume.getExp_pos1())
                    .exp_dur1(buildResume.getExp_dur1())
                    .exp_dec1(buildResume.getExp_dec1())
                    .edu1_school(buildResume.getEdu1_school())
                    .edu1_degree(buildResume.getEdu1_degree())
                    .edu1_year(buildResume.getEdu1_year())
                    .edu1_dur(buildResume.getEdu1_dur())
                    .proj1_title(buildResume.getProj1_title())
                    .proj1_desc(buildResume.getProj1_desc())
                    .proj1_link(buildResume.getProj1_link())
                    .extra_1(buildResume.getExtra_1())
                    .exp_org2(buildResume.getExp_org2())
                    .exp_pos2(buildResume.getExp_pos2())
                    .exp_dur2(buildResume.getExp_dur2())
                    .exp_dec2(buildResume.getExp_dec2())
                    .edu2_school(buildResume.getEdu2_school())
                    .edu2_degree(buildResume.getEdu2_degree())
                    .edu2_year(buildResume.getEdu2_year())
                    .edu2_dur(buildResume.getEdu2_dur())
                    .proj2_title(buildResume.getProj2_title())
                    .proj2_desc(buildResume.getProj2_desc())
                    .proj2_link(buildResume.getProj2_link())
                    .extra_2(buildResume.getExtra_2())
                    .build();

            return new ResponseDto<>(responseDto);
        } catch (Exception e) {
            log.error("Error occurred while fetching the resume: ", e);
            return new ResponseDto<>(Collections.singletonList("Some Error Occurred"));
        }
    }

}
