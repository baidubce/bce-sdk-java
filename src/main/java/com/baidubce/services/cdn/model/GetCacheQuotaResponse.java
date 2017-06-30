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
public class GetCacheQuotaResponse extends CdnResponse {
    private Integer dirRemain;
    private Integer urlRemain;
    private Integer dirQuota;
    private Integer urlQuota;
    
    /**
     * @return dirRemain
     */
    public Integer getDirRemain() {
        return dirRemain;
    }
    
    /**
     * @param dirRemain
     */
    public void setDirRemain(Integer dirRemain) {
        this.dirRemain = dirRemain;
    }
    
    /**
     * @return urlRemain
     */
    public Integer getUrlRemain() {
        return urlRemain;
    }
    
    /**
     * @param urlRemain
     */
    public void setUrlRemain(Integer urlRemain) {
        this.urlRemain = urlRemain;
    }
    
    /**
     * @return dirQuota
     */
    public Integer getDirQuota() {
        return dirQuota;
    }
    
    /**
     * @param dirQuota
     */
    public void setDirQuota(Integer dirQuota) {
        this.dirQuota = dirQuota;
    }
    
    /**
     * @return urlQuota
     */
    public Integer getUrlQuota() {
        return urlQuota;
    }
    
    /**
     * @param urlQuota
     */
    public void setUrlQuota(Integer urlQuota) {
        this.urlQuota = urlQuota;
    }
}
