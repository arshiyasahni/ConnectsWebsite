package com.sep.backend.service.impl;

import com.sep.backend.dto.LoginResponse;
import com.sep.backend.exception.LoginException;

public abstract class UserLogin {
    public abstract LoginResponse getLoginDetails(String email, String password) throws LoginException;

}