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

import com.bptn.models.Post;
import com.bptn.repository.FeedPostRepository;
import com.bptn.request.FeedPostRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;
import java.util.Random;

@Service
public class FeedPostService {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Value("${api.key}")
    private String apiKey;


    @Value("${api.base.url}")
    private String apiBaseUrl;

    @Autowired
    private FeedPostRepository feedPostRepository;
    private static final String POST_TYPE = "news";

    // this main method calls on all the other methods mentioned below

    public Post getPostFromNewsAndSavePost(FeedPostRequest feedPostRequest) {
        String url = buildApiUrl(feedPostRequest);
        LOGGER.debug("News Feed URL = {}", url);
        String feedResult = getFeedFromNewsApi(url);
        return storeFeed(feedResult, feedPostRequest);
    }

    private Post storeFeed(String feedResult, FeedPostRequest feedPostRequest) {

        Post feed = new Post();
        feed.setPost(feedResult);
        feed.setPostType(POST_TYPE);
        feed.setUsernameKey(feedPostRequest.getUserName());
        feed.setPostID(generatePostID(feedPostRequest));

        LOGGER.debug("Feed to be stored:{}", feed);
        return feedPostRepository.save(feed);
    }

    private String generatePostID(FeedPostRequest feedPostRequest) {

        Random random = new Random(System.currentTimeMillis());

        String postID = String.valueOf(random.nextInt()) +
                Objects.hashCode(feedPostRequest.getUserName() + " " + feedPostRequest.getQueryKeyword());

        if (postID.startsWith("-")) {
            return postID.substring(1);
        }
        return postID;
    }

    private String getFeedFromNewsApi(String url) {
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url, String.class);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonElement jsonElement = JsonParser.parseString(result);
        String postDescription = gson.toJson(jsonElement.getAsJsonObject().get("articles").getAsJsonArray().get(0).getAsJsonObject().get("description"));

        return postDescription;
    }


    private String buildApiUrl(FeedPostRequest feedPostRequest) {

        StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append(apiBaseUrl);
        urlBuilder.append("?q=").append(feedPostRequest.getQueryKeyword());
        if (feedPostRequest.getFromDate() != null && !feedPostRequest.getFromDate().isEmpty()) {
            urlBuilder.append("&from=").append(feedPostRequest.getFromDate());
        }
        if (feedPostRequest.getToDate() != null && !feedPostRequest.getToDate().isEmpty()) {
            urlBuilder.append("&to=").append(feedPostRequest.getToDate());
        }
        urlBuilder.append("&apiKey=").append(apiKey);
        return urlBuilder.toString();
    }
}

