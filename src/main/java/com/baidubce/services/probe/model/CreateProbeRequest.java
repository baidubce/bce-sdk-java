package com.baidubce.services.probe.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Setter
@Getter
@Accessors(chain = true)
public class CreateProbeRequest extends AbstractBceRequest {
    /**
     * An ASCII string whose length is less than 64.
     * <p>
     * The request will be idempotent if clientToken is provided.
     * If the clientToken is not specified by the user, a random String generated by default algorithm will be used.
     * See more detail at
     * <a href = "https://bce.baidu.com/doc/BCC/API.html#.E5.B9.82.E7.AD.89.E6.80.A7">
     * BCE API doc</a>
     */
    @JsonIgnore
    private String clientToken;

    /**
     * The name of probe that will be created.
     */
    private String name;

    /**
     * The option param to describe the probe
     */
    private String description;

    /**
     * The id of vpc.
     */
    private String vpcId;

    /**
     * The id of subnet.
     */
    private String subnetId;

    /**
     * Detection types. supports ICMP, TCP, UDP, DNS.
     */
    private String protocol;

    /**
     * Detection frequency values are 10, 20, 30
     */
    private Integer frequency;

    /**
     * probing source IPs
     */
    private List<String> sourceIps;

    /**
     * Number of probing source IPs, when the count exceeds the length of the IP list,
     * the extra IPs will be automatically allocated.
     */
    private Integer sourceIpNum;

    /**
     * Detection of destination IP
     */
    private String destIp;

    /**
     * Detection of destination ports is required for TCP, UDP, and DNS types
     */
    private Integer destPort;

    /**
     * Detection string for UDP type and detection domain for DNS type
     */
    private String payload;


    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

}
