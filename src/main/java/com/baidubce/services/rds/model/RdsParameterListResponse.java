package com.baidubce.services.rds.model;

import com.baidubce.model.AbstractBceResponse;
import org.apache.htrace.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * The response of rds parameter list
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RdsParameterListResponse extends AbstractBceResponse {
    private String etag;

    private List<RdsParameter> parameters;

    public List<RdsParameter> getParameters() {
        return parameters;
    }

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    public void setParameters(List<RdsParameter> parameters) {
        this.parameters = parameters;
    }
}
