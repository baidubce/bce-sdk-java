/**
 * Copyright (C) 2022 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.vca.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.baidubce.model.AbstractBceResponse;

/**
 * Created on 2022/11/15.
 *
 * @author Zhangli Chen (chenzhangli01@baidu.com)
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class McaEmptyResponse extends AbstractBceResponse {

    // empty

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("McaEmptyResponse{");
        sb.append('}');
        return sb.toString();
    }
}
