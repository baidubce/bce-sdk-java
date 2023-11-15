/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.eip.model;

import java.util.Date;
import java.util.List;

/**
 * list of eip model
 */
public class ListEipsResponse extends ListResponse {
    private List<EipModel> eipList;

    public List<EipModel> getEipList() {
        return eipList;
    }

    public void setEipList(List<EipModel> eipList) {
        this.eipList = eipList;
    }

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
         *  The way of eip charging
         *  see more detail at <a href = "https://bce.baidu.com/doc/EIP/API.html#Billing">BCE API doc</a>
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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEip() {
            return eip;
        }

        public void setEip(String eip) {
            this.eip = eip;
        }

        public String getEipId() {
            return eipId;
        }

        public void setEipId(String eipId) {
            this.eipId = eipId;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getInstanceType() {
            return instanceType;
        }

        public void setInstanceType(String instanceType) {
            this.instanceType = instanceType;
        }

        public String getInstanceId() {
            return instanceId;
        }

        public void setInstanceId(String instanceId) {
            this.instanceId = instanceId;
        }

        public int getBandwidthInMbps() {
            return bandwidthInMbps;
        }

        public void setBandwidthInMbps(int bandwidthInMbps) {
            this.bandwidthInMbps = bandwidthInMbps;
        }

        public String getPaymentTiming() {
            return paymentTiming;
        }

        public void setPaymentTiming(String paymentTiming) {
            this.paymentTiming = paymentTiming;
        }

        public String getBillingMethod() {
            return billingMethod;
        }

        public void setBillingMethod(String billingMethod) {
            this.billingMethod = billingMethod;
        }

        public Date getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Date createTime) {
            this.createTime = createTime;
        }

        public Date getExpireTime() {
            return expireTime;
        }

        public void setExpireTime(Date expireTime) {
            this.expireTime = expireTime;
        }
    }
}
