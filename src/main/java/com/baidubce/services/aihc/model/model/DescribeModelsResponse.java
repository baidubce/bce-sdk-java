package com.baidubce.services.aihc.model.model;

import com.baidubce.model.AbstractBceResponse;

import java.util.List;

public class DescribeModelsResponse extends AbstractBceResponse {
    
    private List<Model> models;
    private Integer totalCount;
    
    public List<Model> getModels() {
        return models;
    }

    public void setModels(List<Model> models) {
        this.models = models;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }
    
    public static class Model {
        private String id;
        private String name;
        private String description;
        private String modelType;
        private String framework;
        private String status;
        private String owner;
        private String ownerName;
        private String visibilityScope;
        private String permission;
        private String latestVersionId;
        private String latestVersion;
        private String createdAt;
        private String updatedAt;
        private String modelPath;
        private String modelSize;
        private String inputShape;
        private String outputShape;
        private String precision;

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

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getModelType() {
            return modelType;
        }

        public void setModelType(String modelType) {
            this.modelType = modelType;
        }

        public String getFramework() {
            return framework;
        }

        public void setFramework(String framework) {
            this.framework = framework;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
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

        public String getModelPath() {
            return modelPath;
        }

        public void setModelPath(String modelPath) {
            this.modelPath = modelPath;
        }

        public String getModelSize() {
            return modelSize;
        }

        public void setModelSize(String modelSize) {
            this.modelSize = modelSize;
        }

        public String getInputShape() {
            return inputShape;
        }

        public void setInputShape(String inputShape) {
            this.inputShape = inputShape;
        }

        public String getOutputShape() {
            return outputShape;
        }

        public void setOutputShape(String outputShape) {
            this.outputShape = outputShape;
        }

        public String getPrecision() {
            return precision;
        }

        public void setPrecision(String precision) {
            this.precision = precision;
        }
    }
} 