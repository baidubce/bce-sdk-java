package com.baidubce.services.probe.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class Probe {
    private String name;
    private String probeId;
    private String description;
    private String vpcId;
    private String subnetId;
    private String protocol;
    private Integer frequency;
    private List<String> sourceIps;
    private String destIp;
    private Integer destPort;
    private String payload;
    private String status;
}
