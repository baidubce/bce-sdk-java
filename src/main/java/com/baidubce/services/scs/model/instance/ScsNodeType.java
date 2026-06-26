package com.baidubce.services.scs.model.instance;

import java.util.List;

/**
 * Scs node type model.
 */
public class ScsNodeType {

    private Double instanceFlavor;
    private String nodeType;
    private Integer cpuNum;
    private Double networkThroughputInGbps;
    private Integer peakQps;
    private Integer maxConnections;
    private List<Integer> allowedNodeNumList;
    private Integer minDiskFlavor;
    private Integer maxDiskFlavor;

    public Double getInstanceFlavor() {
        return instanceFlavor;
    }

    public void setInstanceFlavor(Double instanceFlavor) {
        this.instanceFlavor = instanceFlavor;
    }

    public String getNodeType() {
        return nodeType;
    }

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    public Integer getCpuNum() {
        return cpuNum;
    }

    public void setCpuNum(Integer cpuNum) {
        this.cpuNum = cpuNum;
    }

    public Double getNetworkThroughputInGbps() {
        return networkThroughputInGbps;
    }

    public void setNetworkThroughputInGbps(Double networkThroughputInGbps) {
        this.networkThroughputInGbps = networkThroughputInGbps;
    }

    public Integer getPeakQps() {
        return peakQps;
    }

    public void setPeakQps(Integer peakQps) {
        this.peakQps = peakQps;
    }

    public Integer getMaxConnections() {
        return maxConnections;
    }

    public void setMaxConnections(Integer maxConnections) {
        this.maxConnections = maxConnections;
    }

    public List<Integer> getAllowedNodeNumList() {
        return allowedNodeNumList;
    }

    public void setAllowedNodeNumList(List<Integer> allowedNodeNumList) {
        this.allowedNodeNumList = allowedNodeNumList;
    }

    public Integer getMinDiskFlavor() {
        return minDiskFlavor;
    }

    public void setMinDiskFlavor(Integer minDiskFlavor) {
        this.minDiskFlavor = minDiskFlavor;
    }

    public Integer getMaxDiskFlavor() {
        return maxDiskFlavor;
    }

    public void setMaxDiskFlavor(Integer maxDiskFlavor) {
        this.maxDiskFlavor = maxDiskFlavor;
    }
}
