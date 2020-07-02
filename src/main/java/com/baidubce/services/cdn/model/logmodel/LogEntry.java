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

package com.baidubce.services.cdn.model.logmodel;

import com.baidubce.services.cdn.model.JsonObject;

import java.util.Date;

/**
 * @author yixing
 *
 */
public class LogEntry extends JsonObject {
    private String domain;
    private String url;
    private String name;
    private Long size;
    private Date startTime;
    private Date endTime;
    
    /**
     * @return url
     */
    public String getUrl() {
        return url;
    }
    /**
     * @param url
     */
    public void setUrl(String url) {
        this.url = url;
    }
    /**
     * @return name
     */
    public String getName() {
        return name;
    }
    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @return size
     */
    public Long getSize() {
        return size;
    }
    /**
     * @param size
     */
    public void setSize(Long size) {
        this.size = size;
    }
    /**
     * @return startTime
     */
    public Date getStartTime() {
        return startTime;
    }
    /**
     * @param startTime
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
    /**
     * @return endTime
     */
    public Date getEndTime() {
        return endTime;
    }
    /**
     * @param endTime
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * @return domain
     */
    public String getDomain() {
        return domain;
    }

    /**
     * @param domain the domain name
     */
    public void setDomain(String domain) {
        this.domain = domain;
    }

    /**
     * @param domain the domain name
     * @return returns this object
     */
    public LogEntry withDomain(String domain) {
        this.domain = domain;
        return this;
    }
}
