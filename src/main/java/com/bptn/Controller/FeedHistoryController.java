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

import com.bptn.exceptions.InvalidHistoryException;
import com.bptn.exceptions.InvalidUserNameException;
import com.bptn.models.History;
import com.bptn.repository.FeedHistoryRepository;
import com.bptn.service.FeedHistoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class FeedHistoryController {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private FeedHistoryService feedHistoryService;

    @GetMapping(value = "/history/userName/{username}")
    public ResponseEntity<Object> getPostsByUsername(@PathVariable("username") String username) {
        LOGGER.debug("Executing getPostsByUsername API");
        List<History> feeds;
        try {
            feeds = feedHistoryService.getPostsByUsername(username);
        } catch (InvalidUserNameException e) {
            LOGGER.error("Unable to find History by username, cause+{}", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        return new ResponseEntity<>(feeds, HttpStatus.OK);
    }

    @GetMapping(value = "/history/postID/{postID}")
    public ResponseEntity<Object> getPostsByID(@PathVariable("postID") String postID) {
        LOGGER.debug("Executing getPostsByID API");
        History feed;
        try {
            feed = feedHistoryService.getPostByPostID(postID);
        } catch (InvalidHistoryException e) {
            LOGGER.error("Unable to find History by postID, cause={}", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        return new ResponseEntity<>(feed, HttpStatus.OK);
    }

    @GetMapping(value = "/history/postType/{postType}")
    public ResponseEntity<Object> getPostsByPostType(@PathVariable("postType") String postType) {
        LOGGER.debug("Executing getPostsByPostType API");
        List<History> feeds;

        try {
            feeds = feedHistoryService.getPostsByPostType(postType);
        } catch (InvalidHistoryException e) {
            LOGGER.error("Unable to find history by postType, cause={}", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return new ResponseEntity<>(feeds, HttpStatus.OK);
    }

    @DeleteMapping(value = "/history/delete/postType/{postType}")
    public ResponseEntity<Object> deletePostsByPostType(@PathVariable("postType") String postType) {
        LOGGER.debug("Executing deletePostsByPostType API");

        try {
            feedHistoryService.deleteHistoryByPostType(postType);
        } catch (InvalidHistoryException e) {
            LOGGER.error("Unable to find history by postType, cause={}", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.badRequest().body("History post deleted successfully");
    }
}
