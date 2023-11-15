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

package com.baidubce.services.cdn.model.domain;

import com.baidubce.services.cdn.model.CdnRequest;

/**
 * @author yixing
 *
 */
public class GetDomainLogRequest extends CdnRequest {
    private String domain;
    private String startTime;
    private String endTime;
    
    /**
     * @return startTime
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * @param startTime
     */
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    /**
     * @param startTime
     * @return returns this object
     */
    public GetDomainLogRequest withStartTime(String startTime) {
        setStartTime(startTime);
        return this;
    }
   
    /**
     * @return endTime
     */
    public String getEndTime() {
        return endTime;
    }

    /**
     * @param endTime
     */
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    /**
     * @param endTime
     * @return returns this object
     */
    public GetDomainLogRequest withEndTime(String endTime) {
        setEndTime(endTime);
        return this;
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
    public GetDomainLogRequest withDomain(String domain) {
        this.domain = domain;
        return this;
    }

}
