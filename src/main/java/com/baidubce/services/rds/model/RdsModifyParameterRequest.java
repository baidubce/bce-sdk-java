package com.baidubce.services.rds.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import org.apache.htrace.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * Modify the rds parameter
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RdsModifyParameterRequest extends AbstractBceRequest {

    private String etag;
    private String effectiveTime;
    private String instanceId;
    private List<RdsModifyParameter> parameters = new ArrayList<RdsModifyParameter>();

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        super.setRequestCredentials(credentials);
        return this;
    }

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public List<RdsModifyParameter> getParameters() {
        return parameters;
    }

    public void setParameters(List<RdsModifyParameter> parameters) {
        this.parameters = parameters;
    }

    public String getEffectiveTime() {
        return effectiveTime;
    }

    public void setEffectiveTime(String effectiveTime) {
        this.effectiveTime = effectiveTime;
    }

    public RdsModifyParameterRequest addParameters(RdsModifyParameter modifyParameter) {
        if (modifyParameter == null) {
            return this;
        }
        if (parameters == null) {
            parameters = new ArrayList<RdsModifyParameter>();
        }
        parameters.add(modifyParameter);
        return this;
    }
}
