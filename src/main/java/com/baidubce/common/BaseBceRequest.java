/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.common;

import org.apache.http.annotation.NotThreadSafe;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

/**
 * Base class for all BCE web service request objects.
 *
 * @author zhangquan07
 */
@NotThreadSafe
public class BaseBceRequest extends AbstractBceRequest {

    public BaseBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
