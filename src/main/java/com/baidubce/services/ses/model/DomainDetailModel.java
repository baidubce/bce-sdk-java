/*
 * Copyright (c) 2014 Baidu.com, Inc. All Rights Reserved
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
package com.baidubce.services.ses.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The detail model object of domain. It is contained by @link{GetVerifiedDomainResponse} and @link{ListVerifiedDomainResponse}.
 */
public class DomainDetailModel {
    private String domain;
    @JsonProperty("dkim_attr")
    private DkimAttrModel dkimAttr;
    @JsonProperty("domain_attr")
    private DomainAttrModel domainAttr;

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public DkimAttrModel getDkimAttr() {
        return dkimAttr;
    }

    public void setDkimAttr(DkimAttrModel dkimAttr) {
        this.dkimAttr = dkimAttr;
    }

    public DomainAttrModel getDomainAttr() {
        return domainAttr;
    }

    public void setDomainAttr(DomainAttrModel domainAttr) {
        this.domainAttr = domainAttr;
    }

    @Override
    public String toString() {
        return "DomainDetailModel [domain=" + domain + ", dkimAttr=" + dkimAttr + ", domainAttr=" + domainAttr + "]";
    }

}
