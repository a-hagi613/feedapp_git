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

package com.bptn.exceptions;

public class InvalidImageMetaDataException extends Exception {

    public InvalidImageMetaDataException() {
    }

    public InvalidImageMetaDataException(String message) {
        super(message);
    }

    public InvalidImageMetaDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidImageMetaDataException(Throwable cause) {
        super(cause);
    }
}
