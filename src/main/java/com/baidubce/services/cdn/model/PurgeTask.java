/*
 * Copyright 2016 Baidu, Inc.
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

package com.baidubce.services.cdn.model;

/**
 * @author yixing
 *
 */
public class PurgeTask extends JsonObject {
    public static final String TYPE_FILE = "file";
    public static final String TYPE_DIRECTORY = "directory";
    
    private String url;
    private String type;

    /**
     * @param url
     */
    public void setUrl(String url) {
        this.url = url;
    }
    
    /**
     * @return url
     */
    public String getUrl() {
        return url;
    }
    
    /**
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }
    
    /**
     * @return type
     */
    public String getType() {
        return type;
    }
    
    /**
     * @param url
     * @return returns this object
     */
    public PurgeTask withUrl(String url) {
        setUrl(url);
        setType(TYPE_FILE);
        return this;
    }
    
    /**
     * @param url
     * @return returns this object
     */
    public PurgeTask withDirectory(String url) {
        setUrl(url);
        setType(TYPE_DIRECTORY);
        return this;
    }
}
