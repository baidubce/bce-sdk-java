package com.baidubce.services.aihc.model.dataset;

import com.baidubce.model.AbstractBceResponse;

import java.util.List;

public class DescribeDatasetsResponse extends AbstractBceResponse {
    
    private List<Dataset> datasets;
    
    public List<Dataset> getDatasets() {
        return datasets;
    }

    public void setDatasets(List<Dataset> datasets) {
        this.datasets = datasets;
    }
    
    public static class Dataset {
        private String id;
        private String name;
        private String storageType;
        private String storageInstance;
        private String importFormat;
        private String owner;
        private String ownerName;
        private String visibilityScope;
        private String permission;
        private String latestVersionId;
        private String latestVersion;
        private String createdAt;
        private String updatedAt;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getStorageType() {
            return storageType;
        }

        public void setStorageType(String storageType) {
            this.storageType = storageType;
        }

        public String getStorageInstance() {
            return storageInstance;
        }

        public void setStorageInstance(String storageInstance) {
            this.storageInstance = storageInstance;
        }

        public String getImportFormat() {
            return importFormat;
        }

        public void setImportFormat(String importFormat) {
            this.importFormat = importFormat;
        }

        public String getOwner() {
            return owner;
        }

        public void setOwner(String owner) {
            this.owner = owner;
        }

        public String getOwnerName() {
            return ownerName;
        }

        public void setOwnerName(String ownerName) {
            this.ownerName = ownerName;
        }

        public String getVisibilityScope() {
            return visibilityScope;
        }

        public void setVisibilityScope(String visibilityScope) {
            this.visibilityScope = visibilityScope;
        }

        public String getPermission() {
            return permission;
        }

        public void setPermission(String permission) {
            this.permission = permission;
        }

        public String getLatestVersionId() {
            return latestVersionId;
        }

        public void setLatestVersionId(String latestVersionId) {
            this.latestVersionId = latestVersionId;
        }

        public String getLatestVersion() {
            return latestVersion;
        }

        public void setLatestVersion(String latestVersion) {
            this.latestVersion = latestVersion;
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