package com.baidubce.services.scs.model.template;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

public class ScsSystemTemplateRequest extends AbstractBceRequest {
    private String engineVersion;
    private String clusterType;
    private String engine;

    public String getEngineVersion() {
        return engineVersion;
    }

    public void setEngineVersion(String engineVersion) {
        this.engineVersion = engineVersion;
    }

    public String getClusterType() {
        return clusterType;
    }

    public void setClusterType(String clusterType) {
        this.clusterType = clusterType;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        super.setRequestCredentials(credentials);
        return this;
    }
}
