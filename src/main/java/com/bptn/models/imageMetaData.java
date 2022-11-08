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

package com.bptn.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "\"imageMetaData\"")

public class imageMetaData {
    @Id
    @Column(name = "\"imageID\"")
    private String imageID;

    @Column(name = "\"imageName ]\"")
    private String imageName;

    @Column(name = "\"imageSize\"")
    private String imageSize;

    @Column(name = "\"imageFormat\"")
    private String imageFormat;

    @Column(name = "\"imageDate\"")
    private String imageDate;

    @Column(name = "\"resolution\"")
    private String resolution;

    @Column(name = "\"postKey\"")
    private String postKey;


    public imageMetaData() {
        super();
    }

    public imageMetaData(String imageID, String imageName, String imageSize, String imageFormat, String imageDate, String resolution, String postKey) {

//		parameterized constructor
        this.imageID = imageID;
        this.imageName = imageName;
        this.imageSize = imageSize;
        this.imageFormat = imageFormat;
        this.imageDate = imageDate;
        this.resolution = resolution;
        this.postKey = postKey;
    }

    //	getter for imageID
    public String getImageID() {
        return this.imageID;
    }

    //	setter for ImageID
    public void setImageID(String imageID) {
        this.imageID = imageID;
    }

    //	getter for imageName
    public String getImageName() {
        return this.imageName;
    }

    //	setter for imageName
    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    //	getter for imageSize
    public String getImageSize() {
        return this.imageSize;
    }

    //	setter for imageSize
    public void setImageSize(String imageSize) {
        this.imageSize = imageSize;
    }

    //	getter for imageFormat
    public String getImageFormat() {
        return this.imageFormat;
    }

    //	setter for imageFormat
    public void setImageFormat(String imageFormat) {
        this.imageFormat = imageFormat;
    }

    //	getter for imageDate
    public String getImageDate() {
        return this.imageDate;
    }

    //	setter for imageDate
    public void setImageDate(String imageDate) {
        this.imageDate = imageDate;
    }

    //	getter for resolution
    public String getResolution() {
        return this.resolution;
    }

    //	setter for resolution
    public void SetResolution(String resolution) {
        this.resolution = resolution;
    }

    //	getter for postKey
    public String getPostKey() {
        return this.postKey;
    }

    //	setter for postKey
    public void SetPostKey(String postKey) {
        this.postKey = postKey;
    }


}
