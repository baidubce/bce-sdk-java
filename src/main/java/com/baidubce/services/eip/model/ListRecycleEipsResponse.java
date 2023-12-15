/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.eip.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * list of recycle eip model
 */
@Getter
@Setter
public class ListRecycleEipsResponse extends ListResponse {
    private List<RecycleEipModel> eipList;

    @Getter
    @Setter
    @ToString
    public static class RecycleEipModel {
        /**
         * name of eip
         */
        private String name;
        /**
         * eip address
         */
        private String eip;
        /**
         * eip id
         */
        private String eipId;
        /**
         * eip status
         * see more detail at <a href = "https://bce.baidu.com/doc/EIP/API.html#eipStatus"> BCE API doc</a>
         */
        private String status;
        /**
         * eip route type
         */
        private String routeType;
        /**
         * the bandwidth of eip in Mbps
         */
        private int bandwidthInMbps;
        /**
         * The pay time of the payment,
         * see more detail at <a href = "https://bce.baidu.com/doc/EIP/API.html#Billing">BCE API doc</a>
         */
        private String paymentTiming;
        /**
         * The way of eip charging
         * see more detail at <a href = "https://bce.baidu.com/doc/EIP/API.html#Billing">BCE API doc</a>
         */
        private String billingMethod;
        /**
         * offer in recycle time.
         */
        private String recycleTime;
        /**
         * scheduled delete time.
         */
        private String scheduledDeleteTime;
    }

    @Override
    public String toString() {
        return "ListRecycleEipsResponse{" +
                "marker= " + getMarker() + "," +
                "nextMarker= " + getNextMarker() + "," +
                "maxKeys= " + getMaxKeys() + "," +
                "isTruncated= " + getIsTruncated() + "," +
                "eipList=" + eipList +
                '}';
    }
}
