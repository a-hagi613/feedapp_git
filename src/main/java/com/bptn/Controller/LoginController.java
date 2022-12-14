/*
 *
 *  * Copyright (c) 2019. [Acme Corp]
 *  *
 *  * Permission is hereby granted, free of charge, to any person obtaining a copy
 *  * of this software and associated documentation files (the "Software"), to deal
 *  * in the Software without restriction, including without limitation the rights
 *  * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  * copies of the Software, and to permit persons to whom the Software is
 *  * furnished to do so, subject to the following conditions:
 *  *
 *  * The above copyright notice and this permission notice shall be included in
 *  * all copies or substantial portions of the Software.
 *
 */

package com.bptn.models.Controller;

import com.bptn.exceptions.InvalidRequestException;
import com.bptn.exceptions.InvalidUserCredentialsException;
import com.bptn.models.UserID;
import com.bptn.request.LoginRequest;
import com.bptn.request.SignupRequest;
import com.bptn.response.JwtResponse;
import com.bptn.response.LoginResponse;
import com.bptn.service.JwtService;
import com.bptn.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
public class LoginController {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private LoginService loginService;

    @Autowired
    private JwtService jwtService;

    /*
        {
        "username": "jDoe3286",
        "password":"1960"
        }
    */
    @PostMapping("/user/login")
    public ResponseEntity<?> verifyUserCredentials(@RequestBody LoginRequest loginRequest) throws InvalidUserCredentialsException {
        LOGGER.debug("Login request received for username = {} & password = {}", loginRequest.getUsername(), loginRequest.getPassword());
        UserID userID = loginService.verifyUserCredentials(loginRequest);
        if (userID == null) {
            throw new InvalidUserCredentialsException("Invalid User");
        }
        JwtResponse jwtResponse = new JwtResponse(jwtService.generateJwtToken(loginRequest.getUsername()));
        return new ResponseEntity<>(new LoginResponse(userID, jwtResponse), HttpStatus.OK);
    }

    @PostMapping("/user/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest signupRequest) throws InvalidRequestException {
        LOGGER.debug("signup request received = {}", signupRequest);
        UserID userID = loginService.signup(signupRequest);
        return new ResponseEntity<>(userID, HttpStatus.OK);
    }
}