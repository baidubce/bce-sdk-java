package com.baidubce.services.aihc.model.dataset;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

import java.util.List;

public class CreateDatasetRequest extends AbstractBceRequest {

    private String name;
    private String storageType;
    private String storageInstance;
    private String importFormat;
    private String description;
    private String owner;
    private String visibilityScope;
    private List<PermissionEntry> visibilityUser;
    private List<PermissionEntry> visibilityGroup;
    private DatasetVersionEntry initVersionEntry;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getVisibilityScope() {
        return visibilityScope;
    }

    public void setVisibilityScope(String visibilityScope) {
        this.visibilityScope = visibilityScope;
    }

    public List<PermissionEntry> getVisibilityUser() {
        return visibilityUser;
    }

    public void setVisibilityUser(List<PermissionEntry> visibilityUser) {
        this.visibilityUser = visibilityUser;
    }

    public List<PermissionEntry> getVisibilityGroup() {
        return visibilityGroup;
    }

    public void setVisibilityGroup(List<PermissionEntry> visibilityGroup) {
        this.visibilityGroup = visibilityGroup;
    }

    public DatasetVersionEntry getInitVersionEntry() {
        return initVersionEntry;
    }

    public void setInitVersionEntry(DatasetVersionEntry initVersionEntry) {
        this.initVersionEntry = initVersionEntry;
    }

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return CreateDatasetRequest with credentials.
     */
    public CreateDatasetRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    /**
     * 权限条目
     */
    public static class PermissionEntry {
        private String id;
        private String name;
        private String permission;

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

        public String getPermission() {
            return permission;
        }

        public void setPermission(String permission) {
            this.permission = permission;
        }
    }

    /**
     * 数据集版本条目
     */
    public static class DatasetVersionEntry {
        private String description;
        private String storagePath;
        private String mountPath;

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
    }
} 