package com.sep.backend.ControllerTest;

import com.sep.backend.dto.LoginResponse;
import com.sep.backend.enums.Gender;
import com.sep.backend.exception.LoginException;
import com.sep.backend.models.Admin;
import com.sep.backend.repository.AdminRepository;
import com.sep.backend.service.impl.AdminServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


public class AdminControllerTest {

    @Mock
    private AdminRepository adminRepository;

    @InjectMocks
    private AdminServiceImpl adminService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void testGetLoginDetails_Failure() throws LoginException {
        String email = "test@example.com";
        String password = "incorrectPassword";

        when(adminRepository.findFirstByEmailAddressAndPassword(email, password)).thenReturn(null);

        LoginResponse loginResponse = adminService.getLoginDetails(email, password);

        assertFalse(loginResponse.isLogged());
        assertNull(loginResponse.getAge());
        assertNull(loginResponse.getGender());
        // Check other properties
    }
}