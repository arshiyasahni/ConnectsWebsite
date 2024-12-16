package com.sep.backend.ControllerTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.*;

import java.util.Collections;

import com.sep.backend.controller.BuildResumeController;
import com.sep.backend.dto.BuildResumeRequestDto;
import com.sep.backend.dto.BuildResumeResponseDto;
import com.sep.backend.dto.ResponseDto;
import com.sep.backend.constants.UriConstants;
import com.sep.backend.exception.BuildResumeException;
import com.sep.backend.models.BuildResume;
import com.sep.backend.service.IBuildResumeService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class BuildResumeControllerTest {

    @Mock
    private IBuildResumeService iBuildResumeService;

    @InjectMocks
    private BuildResumeController buildResumeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testBuildResume() throws BuildResumeException {
        // Prepare test data
        BuildResumeRequestDto requestDto =new  BuildResumeRequestDto(
                "Abhishek",
                "amittal1311@gmail.com",
                "linkedin.com/iabhimittal",
                 "5144645855",
                 "github.com/Abhi123sh",
                 "java, spring, dbms, c++",
                 "Connects",
                 "Backend Developer",
                 "July 2023 to August 2023",
                 "Worked as the Backend Developer on the development of Connects Careers Service Platform",
                 "Rubiq",
                 "Java Developer",
                 "Sept 2019 to June 2021",
                 "Worked as the Java Developer on the development of MyProject Project Management Platform",
                "Java Plagiarism Detector",
                 "github.com/plagdetection",
                 "Developed a Java based plagiarism detector for source code plagiarism detection.",
                 "AI Face Mask Detection",
                 "github.com/facemaskdetection",
                 "Developed a Face Mask detector to analyze if a person is wearing face mask.",
                 "Concordia University",
                "2023",

                 "Master of Applied Computer Science",
                "Sept 2021 to Aug 2023",
                "Chitkara University",
                 "2020",
                "Aug 2016 to July 2020",
                "Bachelor of Engineering - Computer Science",


                 "I like to play video games.",

                 "I like to listen to music."
);

        // Set properties in the requestDto...

        BuildResume buildResume = new BuildResume(
                requestDto.getName(),
                requestDto.getEmailAddress(),
                requestDto.getPhoneNumber(),
                requestDto.getSkills(),
                requestDto.getGithub(),
                requestDto.getLinkedIn(),
                requestDto.getExp_org1(),
                requestDto.getExp_pos1(),
                requestDto.getExp_dur1(),
                requestDto.getExp_dec1(),
                requestDto.getEdu1_school(),
                requestDto.getEdu1_degree(),
                requestDto.getEdu1_year(),
                requestDto.getEdu1_dur(),
                requestDto.getProj1_title(),
                requestDto.getProj1_desc(),
                requestDto.getProj1_link(),
                requestDto.getExtra_1(),
                requestDto.getExp_org2(),
                requestDto.getExp_pos2(),
                requestDto.getExp_dur2(),
                requestDto.getExp_dec2(),
                requestDto.getEdu2_school(),
                requestDto.getEdu2_degree(),
                requestDto.getEdu2_year(),
                requestDto.getEdu2_dur(),
                requestDto.getProj2_title(),
                requestDto.getProj2_desc(),
                requestDto.getProj2_link(),
                requestDto.getExtra_2());


        // Mock the service method
        when(iBuildResumeService.saveResume(requestDto)).thenReturn(buildResume);

        // Call the controller method
        ResponseDto<BuildResumeResponseDto> responseDto = buildResumeController.buildResume(requestDto);

        // Assertions
        assertNotNull(responseDto);
        assertNotNull(responseDto.getData());
        // Add more assertions as needed...
    }

    @Test
    void testGetBuiltResume() {
        // Prepare test data
        String emailAddress = "amittal1311@gmail.com";
        BuildResumeRequestDto requestDto =new  BuildResumeRequestDto(
                "Abhishek",
                "amittal1311@gmail.com",
                "linkedin.com/iabhimittal",
                "5144645855",
                "github.com/Abhi123sh",
                "java, spring, dbms, c++",
                "Connects",
                "Backend Developer",
                "July 2023 to August 2023",
                "Worked as the Backend Developer on the development of Connects Careers Service Platform",
                "Rubiq",
                "Java Developer",
                "Sept 2019 to June 2021",
                "Worked as the Java Developer on the development of MyProject Project Management Platform",
                "Java Plagiarism Detector",
                "github.com/plagdetection",
                "Developed a Java based plagiarism detector for source code plagiarism detection.",
                "AI Face Mask Detection",
                "github.com/facemaskdetection",
                "Developed a Face Mask detector to analyze if a person is wearing face mask.",
                "Concordia University",
                "2023",

                "Master of Applied Computer Science",
                "Sept 2021 to Aug 2023",
                "Chitkara University",
                "2020",
                "Aug 2016 to July 2020",
                "Bachelor of Engineering - Computer Science",


                "I like to play video games.",

                "I like to listen to music."
        );

        BuildResume buildResume = new BuildResume(
                requestDto.getName(),
                requestDto.getEmailAddress(),
                requestDto.getPhoneNumber(),
                requestDto.getSkills(),
                requestDto.getGithub(),
                requestDto.getLinkedIn(),
                requestDto.getExp_org1(),
                requestDto.getExp_pos1(),
                requestDto.getExp_dur1(),
                requestDto.getExp_dec1(),
                requestDto.getEdu1_school(),
                requestDto.getEdu1_degree(),
                requestDto.getEdu1_year(),
                requestDto.getEdu1_dur(),
                requestDto.getProj1_title(),
                requestDto.getProj1_desc(),
                requestDto.getProj1_link(),
                requestDto.getExtra_1(),
                requestDto.getExp_org2(),
                requestDto.getExp_pos2(),
                requestDto.getExp_dur2(),
                requestDto.getExp_dec2(),
                requestDto.getEdu2_school(),
                requestDto.getEdu2_degree(),
                requestDto.getEdu2_year(),
                requestDto.getEdu2_dur(),
                requestDto.getProj2_title(),
                requestDto.getProj2_desc(),
                requestDto.getProj2_link(),
                requestDto.getExtra_2());
        // Set properties in the buildResume...

        // Mock the service method
        when(iBuildResumeService.getResumeByEmailAddress(emailAddress)).thenReturn(buildResume);

        // Call the controller method
        ResponseDto<BuildResumeResponseDto> responseDto = buildResumeController.getBuiltResume(emailAddress);

        // Assertions
        assertNotNull(responseDto);
        assertNotNull(responseDto.getData());
        // Add more assertions as needed...
    }
}
