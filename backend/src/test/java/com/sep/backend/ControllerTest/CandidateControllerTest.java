package com.sep.backend.ControllerTest;

import com.sep.backend.dto.CandidateRegistrationRequestDto;
import com.sep.backend.dto.LoginResponse;
import com.sep.backend.enums.Gender;
import com.sep.backend.exception.CandidateRegistrationException;
import com.sep.backend.models.Candidate;
import com.sep.backend.repository.CandidateRepository;
import com.sep.backend.service.impl.CandidateServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class CandidateControllerTest {
    @Mock
    private CandidateRepository candidateRepository;

    @InjectMocks
    private CandidateServiceImpl candidateService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveCandidate_Success() throws CandidateRegistrationException {
        CandidateRegistrationRequestDto requestDto = new CandidateRegistrationRequestDto(
                "test",
                "doe",
                "John",
                "4389290336",
                "test@example.com",
                "0",
                25,
                "abc@xyz"
        );
        // Set other properties

        when(candidateRepository.findFirstByEmailAddress("test@example.com")).thenReturn(null);
        when(candidateRepository.save(ArgumentMatchers.any(Candidate.class))).thenReturn(new Candidate());

        Candidate savedCandidate = candidateService.saveCandidate(requestDto);

        assertNotNull(savedCandidate);
        // Additional assertions
    }

    @Test
    public void testSaveCandidate_DuplicateEmail() {
        CandidateRegistrationRequestDto requestDto = new CandidateRegistrationRequestDto( "test",
                "doe",
                "John",
                "4389290336",
                "test@example.com",
                "0",
                25,
                "abc@xyz");
        // Set other properties

        when(candidateRepository.findFirstByEmailAddress("test@example.com")).thenReturn(new Candidate());

        assertThrows(CandidateRegistrationException.class, () -> {
            candidateService.saveCandidate(requestDto);
        });
    }

    @Test
    public void testGetLoginDetails_Success() throws Exception {
        String email = "test@example.com";
        String password = "password123";

        Candidate candidate = new Candidate();
        candidate.setId(1);
        candidate.setAge(30);
        candidate.setGender(Gender.MALE);
        // Set other candidate properties

        when(candidateRepository.findFirstByEmailAddressAndPassword(email, password)).thenReturn(candidate);

        LoginResponse loginResponse = candidateService.getLoginDetails(email, password);

        assertNotNull(loginResponse);
        assertTrue(loginResponse.isLogged());
        assertEquals(1, loginResponse.getId());
        assertEquals(30, loginResponse.getAge());
        assertEquals(Gender.MALE, loginResponse.getGender());
        // Additional assertions
    }

}
