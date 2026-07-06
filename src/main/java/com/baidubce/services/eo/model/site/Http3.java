package com.baidubce.services.eo.model.site;

import com.baidubce.services.eo.model.JsonObject;

/**
 * The HTTP3 configuration of a site.
 */
public class Http3 extends JsonObject {
    /**
     * Whether to enable HTTP3.
     */
    private Boolean enable;

    /**
     * @param enable
     * @return this object
     */
    public Http3 withEnable(Boolean enable) {
        this.enable = enable;
        return this;
    }

    /**
     * @return enable
     */
    public Boolean getEnable() {
        return enable;
    }

    /**
     * @param enable
     */
    public void setEnable(Boolean enable) {
        this.enable = enable;
    }
}
