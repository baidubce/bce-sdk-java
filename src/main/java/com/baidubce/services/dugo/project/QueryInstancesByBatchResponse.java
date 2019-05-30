/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.dugo.project;

import com.baidubce.model.AbstractBceResponse;

import java.sql.Timestamp;
import java.util.List;

/**
 * Response for querying instances by batch
 */
public class QueryInstancesByBatchResponse extends AbstractBceResponse {
    private Meta meta;
    private List<InstanceInfo> instanceInfos;

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public List<InstanceInfo> getInstanceInfos() {
        return instanceInfos;
    }

    public void setInstanceInfos(List<InstanceInfo> instanceInfos) {
        this.instanceInfos = instanceInfos;
    }

    public static class Meta {
        private long total;
        private int pageNum;
        private int pageSize;

        public long getTotal() {
            return total;
        }

        public void setTotal(long total) {
            this.total = total;
        }

        public int getPageNum() {
            return pageNum;
        }

        public void setPageNum(int pageNum) {
            this.pageNum = pageNum;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }
    }

    public static class InstanceInfo {
        private String userId;
        private String projectId;
        private String batchId;
        private String instanceId;
        private String vehicleId;
        private String status;
        private Timestamp bindTime;
        private Timestamp createTime;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getProjectId() {
            return projectId;
        }

        public void setProjectId(String projectId) {
            this.projectId = projectId;
        }

        public String getBatchId() {
            return batchId;
        }

        public void setBatchId(String batchId) {
            this.batchId = batchId;
        }

        public String getInstanceId() {
            return instanceId;
        }

        public void setInstanceId(String instanceId) {
            this.instanceId = instanceId;
        }

        public String getVehicleId() {
            return vehicleId;
        }

        public void setVehicleId(String vehicleId) {
            this.vehicleId = vehicleId;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Timestamp getBindTime() {
            return bindTime;
        }

        public void setBindTime(Timestamp bindTime) {
            this.bindTime = bindTime;
        }

        public Timestamp getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Timestamp createTime) {
            this.createTime = createTime;
        }
    }
}
