package com.baidubce.services.scs.model.template;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

import java.util.List;

public class ScsApplyParamsTemplateRequest extends AbstractBceRequest {
    private List<ScsParameterInfo> parameters;
    private Integer rebootType;
    private Integer extra;
    private List<CacheClusterItem> cacheClusterList;

    public List<ScsParameterInfo> getParameters() {
        return parameters;
    }

    public void setParameters(List<ScsParameterInfo> parameters) {
        this.parameters = parameters;
    }

    public Integer getRebootType() {
        return rebootType;
    }

    public void setRebootType(Integer rebootType) {
        this.rebootType = rebootType;
    }

    public Integer getExtra() {
        return extra;
    }

    public void setExtra(Integer extra) {
        this.extra = extra;
    }

    public List<CacheClusterItem> getCacheClusterList() {
        return cacheClusterList;
    }

    public void setCacheClusterList(List<CacheClusterItem> cacheClusterList) {
        this.cacheClusterList = cacheClusterList;
    }

    public static class CacheClusterItem {
        private String cacheClusterShowId;
        private String region;

        public String getCacheClusterShowId() {
            return cacheClusterShowId;
        }

        public void setCacheClusterShowId(String cacheClusterShowId) {
            this.cacheClusterShowId = cacheClusterShowId;
        }

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        super.setRequestCredentials(credentials);
        return this;
    }
}
