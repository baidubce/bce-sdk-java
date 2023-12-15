package com.baidubce.services.probe.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdateProbeRequest extends AbstractBceRequest {
    private String probeId;
    /**
     * The name of probe that will be created.
     */
    private String name;

    /**
     * The option param to describe the probe
     */
    private String description;

    /**
     * Detection of destination IP
     */
    private String destIp;

    /**
     * Detection of destination ports is required for TCP, UDP, and DNS types
     */
    private String destPort;

    @JsonIgnore
    private String clientToken;

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
