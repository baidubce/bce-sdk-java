package com.baidubce.services.cdn.model.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Hsts {

    /**
     * 配置保存时间，单位为天, 用户输入值为 0 ~ 730 或者-1，为 -1 时表示取消该配置项
     * 必选
     */
    private int maxAge;

    /**
     * 是否包含子域名，默认值为 false
     * 可选
     */
    private Boolean includeSubDomains;

    /**
     * 是否支持preload列表，默认值为 false
     * 可选
     */
    private Boolean preload;

    public int getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }

    public Boolean getIncludeSubDomains() {
        return includeSubDomains;
    }

    public void setIncludeSubDomains(Boolean includeSubDomains) {
        this.includeSubDomains = includeSubDomains;
    }

    public Boolean getPreload() {
        return preload;
    }

    public void setPreload(Boolean preload) {
        this.preload = preload;
    }

    public Hsts withMaxAge(int maxAge) {
        setMaxAge(maxAge);
        return this;
    }

    public Hsts withIncludeSubDomains(boolean includeSubDomains) {
        setIncludeSubDomains(includeSubDomains);
        return this;
    }

    public Hsts withPreload(boolean preload) {
        setPreload(preload);
        return this;
    }
}
