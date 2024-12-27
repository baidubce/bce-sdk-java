package com.baidubce.services.rds.model;

import com.baidubce.model.AbstractBceResponse;
import org.apache.htrace.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * the response of Dialing test interface
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RdsDialingTestResponse extends AbstractBceResponse {
    private Boolean alive;
    private String errors;

    public Boolean getAlive() {
        return alive;
    }
    public void setAlive(Boolean alive) {
        this.alive = alive;
    }

    public String getErrors() {
        return errors;
    }
    public void setErrors(String errors) {
        this.errors = errors;
    }

}
