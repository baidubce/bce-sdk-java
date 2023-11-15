/*
 * Copyright (c) 2021 Baidu.com, Inc. All Rights Reserved
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
package com.baidubce.services.bcd.model;

import com.baidubce.model.AbstractBceResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.base.Objects;

/**
 * @author yangzhensheng
 * @date 2021/6/17
 * @desc the domain status and description response object.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetDomainAuditResponse extends AbstractBceResponse {

    private String domain;

    /**
     * the domian audit status,such as: NORMAL,NONVERIFY,AUDIT,REFUND and so on.
     */
    private String verifyStatus;

    /**
     * the description about the domain
     */
    private String description;

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getVerifyStatus() {
        return verifyStatus;
    }

    public void setVerifyStatus(String verifyStatus) {
        this.verifyStatus = verifyStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("domain", domain)
                .add("verifyStatus", verifyStatus)
                .add("description", description)
                .toString();
    }
}
