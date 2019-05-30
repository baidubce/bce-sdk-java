/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.dugo.project;

import com.baidubce.model.AbstractBceResponse;

import java.util.Date;
import java.util.List;

/**
 * Query batch list response
 */
public class GetBatchListResponse extends AbstractBceResponse {
    private Meta meta;
    private List<BatchInfoVo> data;

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public List<BatchInfoVo> getData() {
        return data;
    }

    public void setData(List<BatchInfoVo> data) {
        this.data = data;
    }

    public static class Meta {
        private long total;
        private List<String> address;

        public long getTotal() {
            return total;
        }

        public void setTotal(long total) {
            this.total = total;
        }

        public List<String> getAddress() {
            return address;
        }

        public void setAddress(List<String> address) {
            this.address = address;
        }
    }

    public static class BatchInfoVo {
        private String id;
        private Integer totalQuota;
        private Date createTime;
        private Date expireTime;
        private String status;
        private String description;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Integer getTotalQuota() {
            return totalQuota;
        }

        public void setTotalQuota(Integer totalQuota) {
            this.totalQuota = totalQuota;
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

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}
