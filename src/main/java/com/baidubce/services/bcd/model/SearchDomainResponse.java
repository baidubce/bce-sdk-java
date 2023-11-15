/*
 * Copyright (C) 2021 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.bcd.model;

import com.baidubce.model.AbstractBceResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchDomainResponse extends AbstractBceResponse {
    private List<DomainStatusResult> domainBasicInfoList;

    public List<DomainStatusResult> getDomainBasicInfoList() {
        return domainBasicInfoList;
    }

    public void setDomainBasicInfoList(List<DomainStatusResult> domainBasicInfoList) {
        this.domainBasicInfoList = domainBasicInfoList;
    }
}
