/*
 * Copyright (C) 2021 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.bvw.model.matlib;

import java.util.Date;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DraftListRequest extends AbstractBceRequest {

    private MatlibTaskStatus status;

    private Date beginTime;

    private Date endTime;

    private int pageNo;

    private int pageSize;

    public static DraftListRequest of(int pageNo, int pageSize) {
        return of(pageNo, pageSize, null, null, null);
    }

    public static DraftListRequest of(int pageNo, int pageSize, MatlibTaskStatus status, Date beginTime, Date endTime) {
        DraftListRequest request = new DraftListRequest();
        request.setStatus(status);
        request.setBeginTime(beginTime);
        request.setEndTime(endTime);
        request.setPageNo(pageNo);
        request.setPageSize(pageSize);
        return request;
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
