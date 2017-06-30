package com.baidubce.services.modbus.model.gateway;

import com.baidubce.model.GenericAccountRequest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateGatewayRequest extends GenericAccountRequest {

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isUseSsl() {
        return useSsl;
    }

    public void setUseSsl(boolean useSsl) {
        this.useSsl = useSsl;
    }

    private String code; // 网关ID，唯一 必填

    private String description; // 网关描述 必填

    private boolean useSsl; // 是否ssl，否则为tcp 必填

}
