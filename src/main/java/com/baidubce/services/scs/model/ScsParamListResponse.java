package com.baidubce.services.scs.model;

import com.baidubce.model.AbstractBceResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Scs param list info
 */
public class ScsParamListResponse extends AbstractBceResponse {

    private List<ScsParam> parameters = new ArrayList<ScsParam>();

    public List<ScsParam> getParameters() {
        return parameters;
    }

    public void setParameters(List<ScsParam> parameters) {
        this.parameters = parameters;
    }
}
