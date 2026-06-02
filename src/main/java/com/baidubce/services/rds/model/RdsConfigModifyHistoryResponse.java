package com.baidubce.services.rds.model;

import com.baidubce.model.AbstractBceResponse;

public class RdsConfigModifyHistoryResponse extends AbstractBceResponse {
    private RdsConfigItemModifyHistory.ConfigItemModifyHistoryList parameters;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RdsConfigModifyHistoryResponse{");
        sb.append("parameters=").append(parameters);
        sb.append('}');
        return sb.toString();
    }

    public RdsConfigItemModifyHistory.ConfigItemModifyHistoryList getParameters() {
        return parameters;
    }

    public void setParameters(RdsConfigItemModifyHistory.ConfigItemModifyHistoryList parameters) {
        this.parameters = parameters;
    }
}
