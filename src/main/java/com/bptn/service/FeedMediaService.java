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
import com.bptn.models.imageMetaData;
import com.bptn.repository.FeedImageMetaDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;


@Service
public class FeedMediaService {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private FeedImageMetaDataRepository feedImageMetaDataRepository;

    public List<imageMetaData> getImageMetaDataByPostKey(String postKey) throws InvalidImageMetaDataException {

        LOGGER.info("retrieving imageMedia from db by postKey");

        List<imageMetaData> media = feedImageMetaDataRepository.findByPostKey(postKey);

        if (media == null) {
            throw new InvalidImageMetaDataException("Postkey dose not exist");
        }

        media = removeEmptyImages(media);
        LOGGER.debug("FeedImageMetaDataService = {}", media);

        return media;

    }

    public imageMetaData getPostsImageMediaByImageID(String imageID) throws InvalidImageMetaDataException {

        LOGGER.info("retrieving image information from db by imageID");

        imageMetaData image = feedImageMetaDataRepository.findByImageID(imageID);

        if (image == null) {
            throw new InvalidImageMetaDataException("Image ID does not exist");
        }

        LOGGER.debug("FeedImageMetaDataService = {}", image);

        return image;
    }

    public imageMetaData createNewImage(imageMetaData imageInfo) {

        if (imageInfo.getImageID() != null) {
//          Optional<imageMetaData> image = feedImageMetaDataRepository.findByImageID(imageInfo.getImageID());
            Optional<imageMetaData> image = Optional.ofNullable(feedImageMetaDataRepository.findByImageID(imageInfo.getImageID()));
            if (image.isPresent()) {
                imageMetaData imageNew = image.get();
                imageNew.setImageID(imageInfo.getImageID());
                imageNew.setImageName(imageInfo.getImageName());
                imageNew.setImageSize(imageInfo.getImageSize());
                imageNew.setImageFormat(imageInfo.getImageFormat());
                imageNew.setImageDate(imageInfo.getImageDate());
                imageNew.setResolution(imageInfo.getResolution());
                imageNew.setPostKey(imageInfo.getPostKey());
                imageNew = feedImageMetaDataRepository.save(imageNew);
                return imageNew;
            }
            else {
                imageInfo = feedImageMetaDataRepository.save(imageInfo);
                return imageInfo;
            }
        }
        else {
            imageInfo = feedImageMetaDataRepository.save(imageInfo);
            return imageInfo;
        }
    }


    private List<imageMetaData> removeEmptyImages(List<imageMetaData> media) {
        List<imageMetaData> resultImages = new LinkedList<>();

        for (imageMetaData img : media) {
            if (img.getImageID() != null && !img.getImageID().isEmpty()) {
                resultImages.add(img);
            }
        }
        return resultImages;
    }
}

