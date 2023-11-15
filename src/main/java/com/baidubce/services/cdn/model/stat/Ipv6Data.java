package com.baidubce.services.cdn.model.stat;

import java.util.Map;

public class Ipv6Data {
    private Long flow;
    private Long bps;
    private Long pv;

    private Map<Integer, Long> code;
    private Map<String, String> distribution;

    public Long getFlow() {
        return flow;
    }

    public void setFlow(Long flow) {
        this.flow = flow;
    }

    public Long getBps() {
        return bps;
    }

    public void setBps(Long bps) {
        this.bps = bps;
    }

    public Long getPv() {
        return pv;
    }

    public void setPv(Long pv) {
        this.pv = pv;
    }

    public Map<Integer, Long> getCode() {
        return code;
    }

    public void setCode(Map<Integer, Long> code) {
        this.code = code;
    }

    public Map<String, String> getDistribution() {
        return distribution;
    }

    public void setDistribution(Map<String, String> distribution) {
        this.distribution = distribution;
    }
}
