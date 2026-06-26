package com.baidubce.services.scs.model.template;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

import java.util.List;

public class ScsParamsTemplateModifyParamsRequest extends AbstractBceRequest {
    private List<ScsParameterInfo> parameters;

    public List<ScsParameterInfo> getParameters() {
        return parameters;
    }

    public void setParameters(List<ScsParameterInfo> parameters) {
        this.parameters = parameters;
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        super.setRequestCredentials(credentials);
        return this;
    }
}
