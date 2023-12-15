/*
 * Copyright (C) 2023 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.eipgroup.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * The request for move out eip from eipgroup.
 */
@Getter
@Setter
public class MoveInRequest extends EipGroupOperateRequest {

    /**
     * move in eip params
     */
    private List<String> eips;

    /**
     * Configure moveInEips for the request.
     *
     * @param eips The list of moveInEipsRequest
     * @return EipNameRequest with specific moveOutEipsRequest
     */
    public MoveInRequest withEips(List<String> eips) {
        this.eips = eips;
        return this;
    }
}
