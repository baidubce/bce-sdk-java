package com.baidubce.services.scs.model.template;

import com.baidubce.model.AbstractBceResponse;

import java.util.List;

public class ScsParamsTemplateListResponse extends AbstractBceResponse {
    private String marker;
    private Integer maxKeys;
    private String nextMarker;
    private Boolean isTruncated;
    private List<Result> result;

    public String getMarker() {
        return marker;
    }

    public void setMarker(String marker) {
        this.marker = marker;
    }

    public Integer getMaxKeys() {
        return maxKeys;
    }

    public void setMaxKeys(Integer maxKeys) {
        this.maxKeys = maxKeys;
    }

    public String getNextMarker() {
        return nextMarker;
    }

    public void setNextMarker(String nextMarker) {
        this.nextMarker = nextMarker;
    }

    public Boolean getIsTruncated() {
        return isTruncated;
    }

    public void setIsTruncated(Boolean truncated) {
        isTruncated = truncated;
    }

    public List<Result> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }

    public static class Result {
        private Integer templateId;
        private String templateShowId;
        private String templateName;
        private Integer parametersNum;
        private String clusterType;
        private String engine;
        private String engineVersion;
        private Integer templateType;
        private Integer needReboot;
        private String comment;
        private String createTime;
        private String updateTime;
        private List<Parameter> parameters;

        public Integer getTemplateId() {
            return templateId;
        }

        public void setTemplateId(Integer templateId) {
            this.templateId = templateId;
        }

        public String getTemplateShowId() {
            return templateShowId;
        }

        public void setTemplateShowId(String templateShowId) {
            this.templateShowId = templateShowId;
        }

        public String getTemplateName() {
            return templateName;
        }

        public void setTemplateName(String templateName) {
            this.templateName = templateName;
        }

        public Integer getParametersNum() {
            return parametersNum;
        }

        public void setParametersNum(Integer parametersNum) {
            this.parametersNum = parametersNum;
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

        public Integer getNeedReboot() {
            return needReboot;
        }

        public void setNeedReboot(Integer needReboot) {
            this.needReboot = needReboot;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public List<Parameter> getParameters() {
            return parameters;
        }

        public void setParameters(List<Parameter> parameters) {
            this.parameters = parameters;
        }
    }

    public static class Parameter {
        private String confName;
        private String confDefault;
        private String confValue;
        private Integer confType;
        private String confRange;
        private Integer confModule;
        private String confDesc;
        private Integer needReboot;
        private String confRedisVersion;
        private Integer confCacheVersion;

        public String getConfName() {
            return confName;
        }

        public void setConfName(String confName) {
            this.confName = confName;
        }

        public String getConfDefault() {
            return confDefault;
        }

        public void setConfDefault(String confDefault) {
            this.confDefault = confDefault;
        }

        public String getConfValue() {
            return confValue;
        }

        public void setConfValue(String confValue) {
            this.confValue = confValue;
        }

        public Integer getConfType() {
            return confType;
        }

        public void setConfType(Integer confType) {
            this.confType = confType;
        }

        public String getConfRange() {
            return confRange;
        }

        public void setConfRange(String confRange) {
            this.confRange = confRange;
        }

        public Integer getConfModule() {
            return confModule;
        }

        public void setConfModule(Integer confModule) {
            this.confModule = confModule;
        }

        public String getConfDesc() {
            return confDesc;
        }

        public void setConfDesc(String confDesc) {
            this.confDesc = confDesc;
        }

        public Integer getNeedReboot() {
            return needReboot;
        }

        public void setNeedReboot(Integer needReboot) {
            this.needReboot = needReboot;
        }

        public String getConfRedisVersion() {
            return confRedisVersion;
        }

        public void setConfRedisVersion(String confRedisVersion) {
            this.confRedisVersion = confRedisVersion;
        }

        public Integer getConfCacheVersion() {
            return confCacheVersion;
        }

        public void setConfCacheVersion(Integer confCacheVersion) {
            this.confCacheVersion = confCacheVersion;
        }
    }
}
