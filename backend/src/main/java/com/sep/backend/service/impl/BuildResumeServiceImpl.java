package com.sep.backend.service.impl;

import com.sep.backend.dto.BuildResumeRequestDto;
import com.sep.backend.exception.BuildResumeException;
import com.sep.backend.models.BuildResume;
import com.sep.backend.repository.BuildResumeRepository;
import com.sep.backend.service.IBuildResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Objects;

/**
 * The type Build Resume service.
 */
@Service
public class BuildResumeServiceImpl implements IBuildResumeService {

    private BuildResumeRepository buildResumeRepository;


    /**
     * Instantiates a new Build Resume service.
     *
     * @param buildResumeRepository the build resume repository
     */
    @Autowired
    public BuildResumeServiceImpl(BuildResumeRepository buildResumeRepository) {
        this.buildResumeRepository = buildResumeRepository;

    }

    @Override
    public BuildResume saveResume(BuildResumeRequestDto buildResumeRequestDto) throws BuildResumeException {

        if (Objects.isNull(buildResumeRequestDto.getEmailAddress()))
            throw new BuildResumeException("Email Address is required");

        if (Objects.nonNull(buildResumeRepository.findFirstByEmailAddress(buildResumeRequestDto.getEmailAddress()))) {
            throw new BuildResumeException("Resume with same email address already exists");
        }

        BuildResume buildResume = BuildResume.builder()
                .name(buildResumeRequestDto.getName())
                .emailAddress(buildResumeRequestDto.getEmailAddress())
                .phoneNumber(buildResumeRequestDto.getPhoneNumber())
                .skills(buildResumeRequestDto.getSkills())
                .github(buildResumeRequestDto.getGithub())
                .linkedIn(buildResumeRequestDto.getLinkedIn())
                .exp_org1(buildResumeRequestDto.getExp_org1())
                .exp_pos1(buildResumeRequestDto.getExp_pos1())
                .exp_dur1(buildResumeRequestDto.getExp_dur1())
                .exp_dec1(buildResumeRequestDto.getExp_dec1())
                .edu1_school(buildResumeRequestDto.getEdu1_school())
                .edu1_degree(buildResumeRequestDto.getEdu1_degree())
                .edu1_year(buildResumeRequestDto.getEdu1_year())
                .edu1_dur(buildResumeRequestDto.getEdu1_dur())
                .proj1_title(buildResumeRequestDto.getProj1_title())
                .proj1_desc(buildResumeRequestDto.getProj1_desc())
                .proj1_link(buildResumeRequestDto.getProj1_link())
                .extra_1(buildResumeRequestDto.getExtra_1())
                .exp_org2(buildResumeRequestDto.getExp_org2())
                .exp_pos2(buildResumeRequestDto.getExp_pos2())
                .exp_dur2(buildResumeRequestDto.getExp_dur2())
                .exp_dec2(buildResumeRequestDto.getExp_dec2())
                .edu2_school(buildResumeRequestDto.getEdu2_school())
                .edu2_degree(buildResumeRequestDto.getEdu2_degree())
                .edu2_year(buildResumeRequestDto.getEdu2_year())
                .edu2_dur(buildResumeRequestDto.getEdu1_dur())
                .proj2_title(buildResumeRequestDto.getProj2_title())
                .proj2_desc(buildResumeRequestDto.getProj2_desc())
                .proj2_link(buildResumeRequestDto.getProj2_link())
                .extra_2(buildResumeRequestDto.getExtra_2())
                .build();

        buildResumeRepository.save(buildResume);
        return buildResume;
    }

    @Override
    public BuildResume getResumeByEmailAddress(String emailAddress) {

        return buildResumeRepository.findFirstByEmailAddress(emailAddress);
    }

}