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

import com.bptn.exceptions.InvalidUserNameException;
import com.bptn.models.UserID;
import com.bptn.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserService {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserRepository userRepository;


    public void validateUserId(String username) throws InvalidUserNameException {

        List<UserID> userIDList = userRepository.findUsername(username);
        if (userIDList == null || userIDList.isEmpty()) {
            LOGGER.error("Username ={} does not exist", username);
            throw new InvalidUserNameException("Username does not exist");
        }
        else {
            LOGGER.debug("Username={} validated", username);

        }
    }


}
