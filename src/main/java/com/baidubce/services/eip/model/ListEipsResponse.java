/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.eip.model;

import com.baidubce.services.bcc.model.TagModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/**
 * list of eip model
 */
@Getter
@Setter
public class ListEipsResponse extends ListResponse {
    private List<EipModel> eipList;

    @Getter
    @Setter
    @ToString
    public static class EipModel {
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
         * bound instance type
         */
        private String instanceType;
        /**
         * bound instance id
         */
        private String instanceId;
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
         * create time
         */
        private Date createTime;
        /**
         * expire time, only assigned when Prepaid
         */
        private Date expireTime;

        /**
         * The tags bound to eip.
         */
        private List<TagModel> tags;
    }

    @Override
    public String toString() {
        return "ListEipsResponse{" +
                "marker= " + getMarker() + "," +
                "nextMarker= " + getNextMarker() + "," +
                "maxKeys= " + getMaxKeys() + "," +
                "isTruncated= " + getIsTruncated() + "," +
                "eipList=" + eipList +
                '}';
    }
}
