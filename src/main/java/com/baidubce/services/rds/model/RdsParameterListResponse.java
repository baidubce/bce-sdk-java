package com.baidubce.services.rds.model;

import com.baidubce.model.AbstractBceResponse;

import java.util.List;

/**
 * The response of rds parameter list
 */
public class RdsParameterListResponse extends AbstractBceResponse {

    private List<RdsParameter> parameters;

    public List<RdsParameter> getParameters() {
        return parameters;
    }

    public void setParameters(List<RdsParameter> parameters) {
        this.parameters = parameters;
    }
}
