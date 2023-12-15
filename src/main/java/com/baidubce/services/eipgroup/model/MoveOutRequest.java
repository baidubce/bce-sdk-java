/*
 * Copyright (C) 2023 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.eipgroup.model;

import com.baidubce.services.eip.model.Billing;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * The request for move out eip from eipgroup.
 */
@Getter
@Setter
public class MoveOutRequest extends EipGroupOperateRequest {

    /**
     * move out eip params
     */
    private List<EipMoveOutModel> moveOutEips;

    @Getter
    @Setter
    public static class EipMoveOutModel {
        /**
         * the ip of move out eip
         */
        private String eip;
        /**
         * the bandwidthInMbps of move out eip.
         * only native eip of group need this parameter.
         */
        private Integer bandwidthInMbps;
        /**
         * the billing of move out eip.
         * only native eip of group need this parameter.
         */
        private Billing billing;
    }

    /**
     * Configure moveOutEips for the request.
     *
     * @param moveOutEips The list of moveOutEipsRequest
     * @return EipNameRequest with specific moveOutEipsRequest
     */
    public MoveOutRequest withMoveOutEips(List<EipMoveOutModel> moveOutEips) {
        this.moveOutEips = moveOutEips;
        return this;
    }
}
