/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.dugo.map;

import com.baidubce.model.AbstractBceResponse;

import java.sql.Timestamp;
import java.util.List;

/**
 * Response for listing fence
 */
public class FenceListResponse extends AbstractBceResponse {
    private long total;
    private List<FenceInfoDigest> fenceInfoDigestList;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<FenceInfoDigest> getFenceInfoDigestList() {
        return fenceInfoDigestList;
    }

    public void setFenceInfoDigestList(List<FenceInfoDigest> fenceInfoDigestList) {
        this.fenceInfoDigestList = fenceInfoDigestList;
    }

    public static class FenceInfoDigest {
        private String fenceId;
        private String fenceName;
        private String fenceType;
        private Integer vehicleCount;
        private Timestamp createTime;

        public String getFenceId() {
            return fenceId;
        }

        public void setFenceId(String fenceId) {
            this.fenceId = fenceId;
        }

        public String getFenceName() {
            return fenceName;
        }

        public void setFenceName(String fenceName) {
            this.fenceName = fenceName;
        }

        public String getFenceType() {
            return fenceType;
        }

        public void setFenceType(String fenceType) {
            this.fenceType = fenceType;
        }

        public Integer getVehicleCount() {
            return vehicleCount;
        }

        public void setVehicleCount(Integer vehicleCount) {
            this.vehicleCount = vehicleCount;
        }

        public Timestamp getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Timestamp createTime) {
            this.createTime = createTime;
        }
    }
}
