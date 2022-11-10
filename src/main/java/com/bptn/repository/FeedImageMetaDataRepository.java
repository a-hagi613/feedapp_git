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

package com.bptn.repository;

import com.bptn.models.ImageMetaData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedImageMetaDataRepository extends JpaRepository<ImageMetaData, String> {
    ImageMetaData findByImageID(String imageId);

    List<ImageMetaData> findByPostKey(String postKey);
}
