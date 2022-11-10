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

import com.bptn.exceptions.InvalidImageMetaDataException;
import com.bptn.models.ImageMetaData;
import com.bptn.service.FeedMediaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class FeedMediaController {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private FeedMediaService feedMediaService;

    @GetMapping(value = "/image/metadata/{postKey}")
    public ResponseEntity<Object> getImageMediaByPostKey(@PathVariable("postKey") String postkey) {

        LOGGER.debug("Executing getImageMediaByPostKey API");

        List<ImageMetaData> imageMetaDataList;

        try {
            imageMetaDataList = feedMediaService.getImageMediaByPostKey(postkey);
        } catch (InvalidImageMetaDataException e) {
            LOGGER.error("Unable to find image metadata by postkey, cause{}", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return new ResponseEntity<>(imageMetaDataList, HttpStatus.OK);

    }

    @GetMapping(value = "/imageInfo/metadata/{imageID}")
    public ResponseEntity<Object> getImageMediaByImageId(@PathVariable("imageID") String imageID) {
        LOGGER.debug("Executing getImageMediaByImageId API ");

        ImageMetaData imageMetaData;

        try {
            imageMetaData = feedMediaService.getPostsImageMediaByImageID(imageID);
        } catch (InvalidImageMetaDataException e) {
            LOGGER.error("Unable to find image metadata by imageID, cause{}", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return new ResponseEntity<>(imageMetaData, HttpStatus.OK);
    }


}
