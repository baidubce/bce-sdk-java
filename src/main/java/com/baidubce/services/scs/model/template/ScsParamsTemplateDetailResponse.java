package com.baidubce.services.scs.model.template;

import com.baidubce.model.AbstractBceResponse;

import java.util.List;

public class ScsParamsTemplateDetailResponse extends AbstractBceResponse {
    private String engineVersion;
    private Integer templateType;
    private String clusterType;
    private Integer needReboot;
    private String templateShowId;
    private String updateTime;
    private Integer templateId;
    private Integer parameterNum;
    private String templateName;
    private String engine;
    private String createTime;
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

    public Integer getNeedReboot() {
        return needReboot;
    }

    public void setNeedReboot(Integer needReboot) {
        this.needReboot = needReboot;
    }

    public String getTemplateShowId() {
        return templateShowId;
    }

    public void setTemplateShowId(String templateShowId) {
        this.templateShowId = templateShowId;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
    }

    public Integer getParameterNum() {
        return parameterNum;
    }

    public void setParameterNum(Integer parameterNum) {
        this.parameterNum = parameterNum;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
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
}
