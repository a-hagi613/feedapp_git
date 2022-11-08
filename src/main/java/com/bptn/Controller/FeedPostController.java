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

package com.bptn.Controller;


import com.bptn.exceptions.InvalidRequestException;
import com.bptn.models.Post;
import com.bptn.request.FeedPostRequest;
import com.bptn.service.FeedPostService;
import com.bptn.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
//import org.springframework.util.StringUtils;
import org.apache.commons.lang3.StringUtils;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
public class FeedPostController {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private FeedPostService feedPostService;

    @Autowired
    private UserService userService;

    @PostMapping(value = "/Posts", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})

    public ResponseEntity<Object> saveFeed(@RequestBody FeedPostRequest feedPostRequest) {

        LOGGER.debug("Executing saveFeed API");
        try {
            validateRequest(feedPostRequest);
            userService.validateUserId(feedPostRequest.getUserName());

            Post feed = feedPostService.getPostFromNewsAndSavePost(feedPostRequest);

            return new ResponseEntity<>(feed, HttpStatus.OK);

        } catch (Exception e) {
            LOGGER.error("Unable to save feed, cause={}", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    private void validateRequest(FeedPostRequest feedPostRequest) throws InvalidRequestException {
        if (StringUtils.isEmpty(feedPostRequest.getFromDate()) && StringUtils.isEmpty(feedPostRequest.getToDate())) {
            throw new InvalidRequestException("Invalid request: FromData or ToData is required");
        }
    }

}
