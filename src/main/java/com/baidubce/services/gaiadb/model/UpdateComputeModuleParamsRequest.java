package com.baidubce.services.gaiadb.model;

import java.util.Map;

public class UpdateComputeModuleParamsRequest extends GenericGaiadbRequest {
    private String timing;
    private Map<String, String> params;
    private String executeAction;

    public String getTiming() {
        return timing;
    }

    public void setTiming(String timing) {
        this.timing = timing;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    public String getExecuteAction() {
        return executeAction;
    }

    public void setExecuteAction(String executeAction) {
        this.executeAction = executeAction;
    }
}
