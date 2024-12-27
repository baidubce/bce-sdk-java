package com.baidubce.services.rds.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The Request of Obtain new purchase price
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RdsGetNewPurchasePriceRequest extends AbstractBceRequest {
    private Instance instance;
    private int duration;
    private int number = 1;
    private String productType;

    public Instance getInstance() {
        return instance;
    }

    public void setInstance(Instance instance) {
        this.instance = instance;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Instance {
        private String engine;
        private String engineVersion;
        private int cpuCount;
        private int allocatedMemoryInGB;
        private int allocatedStorageInGB;
        private Integer nodeAmount;
        private String category;
        private String diskIoType;

        public String getEngine() {
            return engine;
        }

        public void setEngine(String engine) {
            this.engine = engine;
        }

        public String getEngineVersion() {
            return engineVersion;
        }

        public void setEngineVersion(String engineVersion) {
            this.engineVersion = engineVersion;
        }

        public int getCpuCount() {
            return cpuCount;
        }

        public void setCpuCount(int cpuCount) {
            this.cpuCount = cpuCount;
        }

        public int getAllocatedMemoryInGB() {
            return allocatedMemoryInGB;
        }

        public void setAllocatedMemoryInGB(int allocatedMemoryInGB) {
            this.allocatedMemoryInGB = allocatedMemoryInGB;
        }

        public int getAllocatedStorageInGB() {
            return allocatedStorageInGB;
        }

        public void setAllocatedStorageInGB(int allocatedStorageInGB) {
            this.allocatedStorageInGB = allocatedStorageInGB;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getDiskIoType() {
            return diskIoType;
        }

        public void setDiskIoType(String diskIoType) {
            this.diskIoType = diskIoType;
        }

        public Integer getNodeAmount() {
            return nodeAmount;
        }

        public void setNodeAmount(Integer nodeAmount) {
            this.nodeAmount = nodeAmount;
        }
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        super.setRequestCredentials(credentials);
        return this;
    }
}
