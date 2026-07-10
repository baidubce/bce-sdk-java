package com.baidubce.services.eo.model.rule;

import com.baidubce.services.eo.model.JsonObject;

/**
 * The origin range configuration.
 */
public class OriginOptions extends JsonObject {
    /**
     * "force_all" enables origin range, "off" disables it.
     */
    private String range;

    /**
     * The origin part size, case-insensitive, e.g. "512k"/"512K", "1m"/"1M".
     * Required when enabled, absent when disabled.
     */
    private String partSize;

    /**
     * @param range
     * @return this object
     */
    public OriginOptions withRange(String range) {
        this.range = range;
        return this;
    }

    /**
     * @return range
     */
    public String getRange() {
        return range;
    }

    /**
     * @param range
     */
    public void setRange(String range) {
        this.range = range;
    }

    /**
     * @param partSize
     * @return this object
     */
    public OriginOptions withPartSize(String partSize) {
        this.partSize = partSize;
        return this;
    }

    /**
     * @return partSize
     */
    public String getPartSize() {
        return partSize;
    }

    /**
     * @param partSize
     */
    public void setPartSize(String partSize) {
        this.partSize = partSize;
    }
}
