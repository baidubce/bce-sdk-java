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

import com.baidubce.services.cdn.model.CdnResponse;

/**
 * @author yixing
 * update by changxing01 on 19/8/27
 */
public class CreateDomainResponse extends CdnResponse {
    private String insId;
    private String cname;
    private String status;

    /**
     * @return cname
     */
    public String getCname() {
        return cname;
    }
    
    /**
     * @param cname
     */
    public void setCname(String cname) {
        this.cname = cname;
    }

    /**
     * @return insId
     */
    public String getInsId() {
        return insId;
    }

    /**
     * @param insId instance id
     */
    public void setInsId(String insId) {
        this.insId = insId;
    }

    /**
     * @return status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status create status
     */
    public void setStatus(String status) {
        this.status = status;
    }
}
