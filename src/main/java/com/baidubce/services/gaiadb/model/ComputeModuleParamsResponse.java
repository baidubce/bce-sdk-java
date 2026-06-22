package com.baidubce.services.gaiadb.model;

import java.util.List;
import java.util.Map;

public class ComputeModuleParamsResponse extends GenericGaiadbResponse {
    private List<Map<String, Object>> params;
    private Map<String, Object> result;

    public List<Map<String, Object>> getParams() {
        return params;
    }

    public void setParams(List<Map<String, Object>> params) {
        this.params = params;
    }

    public Map<String, Object> getResult() {
        return result;
    }

    public void setResult(Map<String, Object> result) {
        this.result = result;
    }
}
