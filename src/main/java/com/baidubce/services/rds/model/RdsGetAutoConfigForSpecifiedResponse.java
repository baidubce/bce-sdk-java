package com.baidubce.services.rds.model;

import com.baidubce.model.AbstractBceResponse;
import org.apache.htrace.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The response of Get the automatic scaling configuration information for the specified instance
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RdsGetAutoConfigForSpecifiedResponse extends AbstractBceResponse {

    Integer autoResizeDisk;
    Integer freeSpaceThreshold;
    Integer diskMaxLimit;
    Integer extendStepPercent;

    public Integer getAutoResizeDisk() {
        return autoResizeDisk;
    }
    public void setAutoResizeDisk(Integer autoResizeDisk) {
        this.autoResizeDisk = autoResizeDisk;
    }

    public Integer getFreeSpaceThreshold() {
        return freeSpaceThreshold;
    }
    public void setFreeSpaceThreshold(Integer freeSpaceThreshold) {
        this.freeSpaceThreshold = freeSpaceThreshold;
    }

    public Integer getDiskMaxLimit() {
        return diskMaxLimit;
    }
    public void setDiskMaxLimit(Integer diskMaxLimit) {
        this.diskMaxLimit = diskMaxLimit;
    }

    public Integer getExtendStepPercent() {
        return extendStepPercent;
    }
    public void setExtendStepPercent(Integer extendStepPercent) {
        this.extendStepPercent = extendStepPercent;
    }


}
