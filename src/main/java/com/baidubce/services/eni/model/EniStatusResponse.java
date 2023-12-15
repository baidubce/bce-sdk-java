/*
 * Copyright (C) 2023 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.eni.model;

import com.baidubce.model.AbstractBceResponse;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EniStatusResponse extends AbstractBceResponse {
    private String eniStatus;

    @Override
    public String toString() {
        return "EniStatusResponse{" +
                "eniStatus='" + eniStatus + '\'' +
                '}';
    }
}
