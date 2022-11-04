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

import com.bptn.exceptions.InvalidPostException;
import com.bptn.exceptions.InvalidUserNameException;
import com.bptn.models.Post;
import com.bptn.repository.FeedRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;


@Service
public class FeedService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private FeedRepository feedRepository;

    @Autowired
    private UserService userService;

    public List<Post> getPostByUsername(String username) throws InvalidUserNameException {
        logger.info("Getting post from database by username");

        userService.validateUserId(username);

        List<Post> posts = feedRepository.findByUsername(username);
        posts = removeEmptyPosts(posts);
        logger.debug("Feeds = {}", posts);
        return posts;
    }

    public Post getPostsByPostID(String postID) throws InvalidPostException {

        logger.info("Getting Posts from database by postID ");


        Post post = feedRepository.findByPostID(postID);

        if (post == null) {
            throw new InvalidPostException("PostID does not exist");
        }

        logger.debug("Feed = {}", post);
        return post;
    }

    public List<Post> getPostsByPostType(String postType) throws InvalidPostException {
        logger.info("Getting Posts from database by postType");

        List<Post> posts = feedRepository.findByPostType(postType);

        if (posts == null) {
            throw new InvalidPostException("PostType does not exist");
        }

        posts = removeEmptyPosts(posts);

        logger.debug("Feed = {}", posts);

        return posts;
    }


    private List<Post> removeEmptyPosts(List<Post> posts) {
        List<Post> resultPosts = new LinkedList<>();

        for (Post post : posts) {
            if (post.getPost() != null && !post.getPost().isEmpty()) {
                resultPosts.add(post);
            }
        }
        return resultPosts;
    }
}
