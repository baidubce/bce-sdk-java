/**
 * Copyright (C) 2022 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.vca.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

/**
 * Created on 2022/11/15.
 *
 * @author Zhangli Chen (chenzhangli01@baidu.com)
 */
public class McaEmptyRequest extends AbstractBceRequest {

    // empty

    @Override
    public McaEmptyRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("McaEmptyRequest{");
        sb.append('}');
        return sb.toString();
    }
}
