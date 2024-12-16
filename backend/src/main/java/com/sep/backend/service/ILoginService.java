package com.sep.backend.service;


import com.sep.backend.dto.LoginResponse;
import com.sep.backend.exception.LoginException;

public interface ILoginService {
    LoginResponse getLoginDetails(String email, String password, String userType) throws LoginException;

}
