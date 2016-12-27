/*
 * Copyright 2014 Baidu, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.baidubce.services.bos.model;

/**
 * Contains the data returned by Baidu Bos from the <code>putObject</code> operation.
 * Use this request to access information about the new object created from the
 * <code>putObject</code> request, such as its ETag and optional version ID.
 */
public class AppendObjectResponse extends PutObjectResponse {

    /**
     * The contentMd5 is the md5 of the object that has been uploaded to bos.
     */
    private String contentMd5;

    /**
     * The nextOffset indicates that which position to append to the object.
     */
    private Long nextAppendOffset;

    public String getContentMd5() {
        return contentMd5;
    }

    public void setContentMd5(String contentMd5) {
        this.contentMd5 = contentMd5;
    }

    public Long getNextAppendOffset() {
        return nextAppendOffset;
    }

    public void setNextAppendOffset(Long nextAppendOffset) {
        this.nextAppendOffset = nextAppendOffset;
    }

}