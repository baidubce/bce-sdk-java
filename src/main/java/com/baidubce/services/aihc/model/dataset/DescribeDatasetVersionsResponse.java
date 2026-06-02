package com.baidubce.services.aihc.model.dataset;

import com.baidubce.model.AbstractBceResponse;

import java.util.List;

public class DescribeDatasetVersionsResponse extends AbstractBceResponse {
    
    private Integer totalCount;
    private List<DatasetVersionEntry> versions;

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public List<DatasetVersionEntry> getVersions() {
        return versions;
    }

    public void setVersions(List<DatasetVersionEntry> versions) {
        this.versions = versions;
    }

    /**
     * 数据集版本条目
     */
    public static class DatasetVersionEntry {
        private String id;
        private String version;
        private String description;
        private String storagePath;
        private String mountPath;
        private String createUser;
        private String createUserName;
        private String createdAt;
        private String updatedAt;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getStoragePath() {
            return storagePath;
        }

        public void setStoragePath(String storagePath) {
            this.storagePath = storagePath;
        }

        public String getMountPath() {
            return mountPath;
        }

        public void setMountPath(String mountPath) {
            this.mountPath = mountPath;
        }

        public String getCreateUser() {
            return createUser;
        }

        public void setCreateUser(String createUser) {
            this.createUser = createUser;
        }

        public String getCreateUserName() {
            return createUserName;
        }

        public void setCreateUserName(String createUserName) {
            this.createUserName = createUserName;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }
    }
} 