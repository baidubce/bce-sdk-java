package com.baidubce.services.rds.model;

import com.baidubce.model.AbstractBceResponse;
import org.apache.htrace.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The response of Instance Update storage automatic expansion configuration
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RdsUpdateStorageAutoExpansionConfigResPonse extends AbstractBceResponse {
    private int freeSpaceThreshold;
    private int diskMaxLimit;

    public int getFreeSpaceThreshold() {
        return freeSpaceThreshold;
    }
    public void setFreeSpaceThreshold(int freeSpaceThreshold) {
        this.freeSpaceThreshold = freeSpaceThreshold;
    }

    public int getDiskMaxLimit() {
        return diskMaxLimit;
    }
    public void setDiskMaxLimit(int diskMaxLimit) {
        this.diskMaxLimit = diskMaxLimit;
    }


}
