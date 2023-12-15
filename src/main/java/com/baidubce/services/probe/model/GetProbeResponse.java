package com.baidubce.services.probe.model;

import com.baidubce.model.AbstractBceResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class GetProbeResponse extends AbstractBceResponse {
    private String name;
    private String probeId;
    private String description;
    private String destIp;
    private String destPort;
    private String frequency;
    private String payload;
    private String protocol;
    private List<String> sourceIps;
    private String status;
    private String subnetId;
}
