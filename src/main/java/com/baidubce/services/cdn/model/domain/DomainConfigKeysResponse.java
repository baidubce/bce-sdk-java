package com.baidubce.services.cdn.model.domain;

import com.baidubce.services.cdn.model.CdnResponse;

import java.util.Map;

public class DomainConfigKeysResponse extends CdnResponse {
    /**
     * 能复制的配置项列表
     */
    private Map<String, ConfigKey> configs;

    public DomainConfigKeysResponse() {
    }

    public Map<String, ConfigKey> getConfigs() {
        return configs;
    }

    public void setConfigs(Map<String, ConfigKey> configs) {
        this.configs = configs;
    }
}
