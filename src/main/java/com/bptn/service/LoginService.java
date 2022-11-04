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

package com.bptn.service;

import com.bptn.exceptions.InvalidUserCredentialsException;
import com.bptn.models.UserID;
import com.bptn.repository.LoginRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class LoginService {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private LoginRepository loginRepository;

    public String validateUserCredentials(UserID userCred) throws InvalidUserCredentialsException {

        List<UserID> userEntity = loginRepository.getUserById(userCred.getUsername());
        for (UserID u : userEntity) {
            LOGGER.info("Retrieving username from the database");

            if (u.getUsername().equals(userCred.getUsername())) {
                if (u.getUserPassword().equals(userCred.getUserPassword())) {
                    return "Login successful.";
                }
                else {
                    return "Password incorrect" + new InvalidUserCredentialsException("Password does not exist");
                }
            }

        }
        return "Username incorrect" + new InvalidUserCredentialsException("Username does not exist");


    }

}
