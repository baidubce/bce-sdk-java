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

public class DescribeIpResponse extends CdnResponse {
    private boolean cdnIP;
    private String isp;
    private String region;
    
    public boolean isCdnIP() {
        return cdnIP;
    }
    
    public void setCdnIP(boolean cdnIP) {
        this.cdnIP = cdnIP;
    }
    
    public String getIsp() {
        return isp;
    }
    
    public void setIsp(String isp) {
        this.isp = isp;
    }
    
    public String getRegion() {
        return region;
    }
    
    public void setRegion(String region) {
        this.region = region;
    }
}
