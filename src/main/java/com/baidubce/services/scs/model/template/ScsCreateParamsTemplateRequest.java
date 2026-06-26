package com.baidubce.services.scs.model.template;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

import java.util.List;

public class ScsCreateParamsTemplateRequest extends AbstractBceRequest {
    private String engineVersion;
    private Integer templateType;
    private String clusterType;
    private String engine;
    private String name;
    private String comment;
    private List<ScsParameterInfo> parameters;

    public String getEngineVersion() {
        return engineVersion;
    }

    public void setEngineVersion(String engineVersion) {
        this.engineVersion = engineVersion;
    }

    public Integer getTemplateType() {
        return templateType;
    }

    public void setTemplateType(Integer templateType) {
        this.templateType = templateType;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<ScsParameterInfo> getParameters() {
        return parameters;
    }

    public void setParameters(List<ScsParameterInfo> parameters) {
        this.parameters = parameters;
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        super.setRequestCredentials(credentials);
        return this;
    }
}
