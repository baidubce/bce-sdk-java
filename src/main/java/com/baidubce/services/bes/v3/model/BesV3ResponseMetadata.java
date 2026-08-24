package com.baidubce.services.bes.v3.model;

import com.baidubce.BceResponseMetadata;

public class BesV3ResponseMetadata extends BceResponseMetadata {

    private String cacheControl;

    private String pragma;

    private String xContentTypeOptions;

    public String getCacheControl() {
        return this.cacheControl;
    }

    public void setCacheControl(String cacheControl) {
        this.cacheControl = cacheControl;
    }

    public String getPragma() {
        return this.pragma;
    }

    public void setPragma(String pragma) {
        this.pragma = pragma;
    }

    public String getxContentTypeOptions() {
        return this.xContentTypeOptions;
    }

    public void setxContentTypeOptions(String xContentTypeOptions) {
        this.xContentTypeOptions = xContentTypeOptions;
    }
}
