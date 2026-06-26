package com.baidubce.services.scs.model.template;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

import java.util.List;

public class ScsParamsTemplateDeleteParamsRequest extends AbstractBceRequest {
    private List<String> parameters;

    public List<String> getParameters() {
        return parameters;
    }

    public void setParameters(List<String> parameters) {
        this.parameters = parameters;
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        super.setRequestCredentials(credentials);
        return this;
    }
}
