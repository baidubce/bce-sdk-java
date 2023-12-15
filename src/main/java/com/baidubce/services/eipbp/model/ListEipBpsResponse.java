/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.eipbp.model;

import com.baidubce.services.eip.model.ListResponse;
import lombok.ToString;

import java.util.List;

/**
 * the response of listing eipBps.
 */
public class ListEipBpsResponse extends ListResponse {
    private List<BandwidthPackage> bpList;

    public List<BandwidthPackage> getBpList() {
        return bpList;
    }

    public void setBpList(List<BandwidthPackage> bpList) {
        this.bpList = bpList;
    }

    @ToString
    public static class BandwidthPackage {
        /**
         * The name of eipBp.
         */
        private String name;
        /**
         * The id of eipBp.
         */
        private String id;
        /**
         * The type of eipBp binding.'eip' or 'eipgroup'.
         */
        private String bindType;
        /**
         * The bandwidthInMbps of eipBp.
         */
        private Integer bandwidthInMbps;
        /**
         * The instanceId of eipBp binding.
         */
        private String instanceId;
        /**
         * The ips of eipBp binding. If bindType is eip,this param contains only one ip address,
         * else contains all ip address in eipgroup.
         */
        private List<String> eips;
        /**
         * The eipBp createTime. UTC format.
         */
        private String createTime;
        /**
         * The eipBp autoReleaseTime. UTC format. If not set, it is the same as the expiration time of the bound resource.
         */
        private String autoReleaseTime;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getBindType() {
            return bindType;
        }

        public void setBindType(String bindType) {
            this.bindType = bindType;
        }

        public Integer getBandwidthInMbps() {
            return bandwidthInMbps;
        }

        public void setBandwidthInMbps(Integer bandwidthInMbps) {
            this.bandwidthInMbps = bandwidthInMbps;
        }

        public String getInstanceId() {
            return instanceId;
        }

        public void setInstanceId(String instanceId) {
            this.instanceId = instanceId;
        }

        public List<String> getEips() {
            return eips;
        }

        public void setEips(List<String> eips) {
            this.eips = eips;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getAutoReleaseTime() {
            return autoReleaseTime;
        }

        public void setAutoReleaseTime(String autoReleaseTime) {
            this.autoReleaseTime = autoReleaseTime;
        }
    }

    @Override
    public String toString() {
        return "ListEipBpsResponse{" +
                "marker= " + getMarker() + "," +
                "nextMarker= " + getNextMarker() + "," +
                "maxKeys= " + getMaxKeys() + "," +
                "isTruncated= " + getIsTruncated() + "," +
                "bpList=" + bpList +
                '}';
    }
}
