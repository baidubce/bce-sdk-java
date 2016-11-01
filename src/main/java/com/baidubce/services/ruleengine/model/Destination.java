package com.baidubce.services.ruleengine.model;

import com.baidubce.model.GenericAccountRequest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by yuanyoujun on 2016/10/9.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Destination extends GenericAccountRequest {
    private String uuid;
    private String ruleUuid;
    private String value;
    private DestinationKind kind;
    private String status;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getRuleUuid() {
        return ruleUuid;
    }

    public void setRuleUuid(String ruleUuid) {
        this.ruleUuid = ruleUuid;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public DestinationKind getKind() {
        return kind;
    }

    public void setKind(DestinationKind kind) {
        this.kind = kind;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
