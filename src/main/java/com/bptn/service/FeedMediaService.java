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

import com.bptn.exceptions.InvalidImageMetaDataException;
import com.bptn.models.ImageMetaData;
import com.bptn.repository.FeedImageMetaDataRepository;
import com.bptn.request.FeedMediaRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;


@Service
public class FeedMediaService {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private FeedImageMetaDataRepository feedImageMetaDataRepository;

    public List<ImageMetaData> getImageMediaByPostKey(String postkey) throws InvalidImageMetaDataException {

        LOGGER.info("retrieving imageMedia from db by postKey");

        List<ImageMetaData> media = feedImageMetaDataRepository.findByPostKey(postkey);

        if (media == null) {
            throw new InvalidImageMetaDataException("Postkey dose not exist");
        }

        media = removeEmptyImages(media);
        LOGGER.debug("FeedImageMetaDataService = {}", media);

        return media;

    }

    public ImageMetaData getPostsImageMediaByImageID(String imageID) throws InvalidImageMetaDataException {

        LOGGER.info("retrieving image information from db by imageID");

        ImageMetaData image = feedImageMetaDataRepository.findByImageID(imageID);

        if (image == null) {
            throw new InvalidImageMetaDataException("Image ID does not exist");
        }

        LOGGER.debug("FeedImageMetaDataService = {}", image);

        return image;
    }

    public ImageMetaData createNewImage(FeedMediaRequest imageInfo) {

        String postKey = imageInfo.getPostKey();
        String imageID = generateFeedMetaDataID(postKey);


        Optional<ImageMetaData> image = feedImageMetaDataRepository.findById(imageID);
        ImageMetaData imageNew;

        if (image.isPresent()) {
            imageNew = image.get();
        }
        else {
            imageNew = new ImageMetaData();
            imageNew.setImageID(imageID);
        }

        imageNew.setImageName(imageInfo.getImageName());
        imageNew.setImageSize(imageInfo.getImageSize());
        imageNew.setImageFormat(imageInfo.getImageFormat());
        imageNew.setImageDate(LocalDate.now() + "");
        imageNew.setResolution(imageInfo.getResolution());
        imageNew.setPostKey(imageInfo.getPostKey());
        imageNew = feedImageMetaDataRepository.save(imageNew);

        return imageNew;
    }


    private List<ImageMetaData> removeEmptyImages(List<ImageMetaData> media) {
        List<ImageMetaData> resultImages = new LinkedList<>();

        for (ImageMetaData img : media) {
            if (img.getImageID() != null && !img.getImageID().isEmpty()) {
                resultImages.add(img);
            }
        }
        return resultImages;
    }


    private String generateFeedMetaDataID(String postKey) {
        Random random = new Random(System.currentTimeMillis());
        String imageID = String.valueOf(random.nextInt()) +
                Objects.hashCode(postKey);

        if (imageID.startsWith("-")) {
            return imageID.substring(1);
        }
        return imageID;
    }
}

