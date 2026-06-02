package com.baidubce.services.aihc.model.model;

import com.baidubce.model.AbstractBceResponse;

/**
 * 获取模型详情的响应
 */
public class DescribeModelResponse extends AbstractBceResponse {

    /**
     * 模型名称
     */
    private String name;

    /**
     * 模型ID
     */
    private String id;

    /**
     * 模型创建时的来源
     */
    private String initSource;

    /**
     * 模型格式
     */
    private String modelFormat;

    /**
     * 描述
     */
    private String description;

    /**
     * 创建时间
     */
    private String createdAt;

    /**
     * 更新时间
     */
    private String updatedAt;

    /**
     * 所有者
     */
    private String owner;

    /**
     * 所有者名称
     */
    private String ownerName;

    /**
     * 可见范围
     */
    private String visibilityScope;

    /**
     * 最新版本的详细信息
     */
    private ModelVersionEntry versionEntry;

    /**
     * 模型版本条目
     */
    public static class ModelVersionEntry {
        /**
         * 版本号
         */
        private String version;

        /**
         * 版本ID
         */
        private String id;

        /**
         * 来源
         */
        private String source;

        /**
         * 任务ID
         */
        private String taskId;

        /**
         * 存储桶
         */
        private String storageBucket;

        /**
         * 存储路径
         */
        private String storagePath;

        /**
         * 模型指标
         */
        private String modelMetrics;

        /**
         * 描述
         */
        private String description;

        /**
         * 创建时间
         */
        private String createdAt;

        /**
         * 创建用户
         */
        private String createUser;

        /**
         * 创建用户名
         */
        private String createUserName;

        // Getters and Setters
        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getTaskId() {
            return taskId;
        }

        public void setTaskId(String taskId) {
            this.taskId = taskId;
        }

        public String getStorageBucket() {
            return storageBucket;
        }

        public void setStorageBucket(String storageBucket) {
            this.storageBucket = storageBucket;
        }

        public String getStoragePath() {
            return storagePath;
        }

        public void setStoragePath(String storagePath) {
            this.storagePath = storagePath;
        }

        public String getModelMetrics() {
            return modelMetrics;
        }

        public void setModelMetrics(String modelMetrics) {
            this.modelMetrics = modelMetrics;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
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
    }

    // Getters and Setters
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

    public String getInitSource() {
        return initSource;
    }

    public void setInitSource(String initSource) {
        this.initSource = initSource;
    }

    public String getModelFormat() {
        return modelFormat;
    }

    public void setModelFormat(String modelFormat) {
        this.modelFormat = modelFormat;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public ModelVersionEntry getVersionEntry() {
        return versionEntry;
    }

    public void setVersionEntry(ModelVersionEntry versionEntry) {
        this.versionEntry = versionEntry;
    }
} 