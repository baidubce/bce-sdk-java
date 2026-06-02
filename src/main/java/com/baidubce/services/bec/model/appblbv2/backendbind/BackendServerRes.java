package com.baidubce.services.bec.model.appblbv2.backendbind;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BackendServerRes {
    private String instanceId;
    private String privateIp;
    private String weight;
    private List<Port> portList;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    static class Port{
        private int listenerPort;
        private String listenerPortType;
        private int healthCheckPort;
        private String healthCheckPortType;
        private String portType;
        private String status;
        private String backendPort;
        private String policyId;
        private String portId;
    }
}
