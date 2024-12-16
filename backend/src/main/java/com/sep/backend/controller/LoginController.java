package com.sep.backend.controller;

import com.sep.backend.constants.UriConstants;
import com.sep.backend.dto.LoginResponse;
import com.sep.backend.dto.ResponseDto;
import com.sep.backend.exception.LoginException;
import com.sep.backend.service.ILoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@Slf4j
@RequestMapping(UriConstants.BASE_URL)
public class LoginController {
    private ILoginService iLoginService;

    @Autowired
    LoginController(ILoginService iLoginService) {
        this.iLoginService = iLoginService;
    }

    @RequestMapping(method = RequestMethod.GET, value = UriConstants.LOGIN_USER)
    public ResponseDto<LoginResponse> getLoginResponse(@RequestParam(name = "email") String email,
                                                       @RequestParam(name = "password") String password, @RequestParam(name = "userType") String userType) {
        try {
            LoginResponse loginResponse = iLoginService.getLoginDetails(email, password, userType);
            return new ResponseDto<>(loginResponse);
        } catch (LoginException ex) {
            LoginResponse dto = new LoginResponse();
            dto.setLogged(false);
            return new ResponseDto<>(dto);
        }
    }
}