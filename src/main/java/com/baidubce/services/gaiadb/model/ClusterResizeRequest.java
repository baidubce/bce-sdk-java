package com.baidubce.services.gaiadb.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class ClusterResizeRequest extends GenericGaiadbRequest {
    private String clusterId;
    private String orderId;
    private String resizeType;
    private String serviceType;
    private String slaveId;
    private Integer count;
    private Integer allocatedCpuInCore;
    private Integer allocatedMemoryInMB;
    private String instanceClass;
    private String engine;
    private String executeAction;
    private String azone;
    @JsonProperty("interface")
    private Map<String, Object> interfacee;
    private String interfaceId;
    private Integer proxyAmount;
    private String interfaceFlavor;
    private String accessType;
    private Integer duration;
    private String clusterClass;
    private String resourceType;
    private String sliceId;

    public String getClusterId() {
        return clusterId;
    }

    public void setClusterId(String clusterId) {
        this.clusterId = clusterId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getResizeType() {
        return resizeType;
    }

    public void setResizeType(String resizeType) {
        this.resizeType = resizeType;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getSlaveId() {
        return slaveId;
    }

    public void setSlaveId(String slaveId) {
        this.slaveId = slaveId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getAllocatedCpuInCore() {
        return allocatedCpuInCore;
    }

    public void setAllocatedCpuInCore(Integer allocatedCpuInCore) {
        this.allocatedCpuInCore = allocatedCpuInCore;
    }

    public Integer getAllocatedMemoryInMB() {
        return allocatedMemoryInMB;
    }

    public void setAllocatedMemoryInMB(Integer allocatedMemoryInMB) {
        this.allocatedMemoryInMB = allocatedMemoryInMB;
    }

    public String getInstanceClass() {
        return instanceClass;
    }

    public void setInstanceClass(String instanceClass) {
        this.instanceClass = instanceClass;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getExecuteAction() {
        return executeAction;
    }

    public void setExecuteAction(String executeAction) {
        this.executeAction = executeAction;
    }

    public String getAzone() {
        return azone;
    }

    public void setAzone(String azone) {
        this.azone = azone;
    }

    public Map<String, Object> getInterfacee() {
        return interfacee;
    }

    public void setInterfacee(Map<String, Object> interfacee) {
        this.interfacee = interfacee;
    }

    public String getInterfaceId() {
        return interfaceId;
    }

    public void setInterfaceId(String interfaceId) {
        this.interfaceId = interfaceId;
    }

    public Integer getProxyAmount() {
        return proxyAmount;
    }

    public void setProxyAmount(Integer proxyAmount) {
        this.proxyAmount = proxyAmount;
    }

    public String getInterfaceFlavor() {
        return interfaceFlavor;
    }

    public void setInterfaceFlavor(String interfaceFlavor) {
        this.interfaceFlavor = interfaceFlavor;
    }

    public String getAccessType() {
        return accessType;
    }

    public void setAccessType(String accessType) {
        this.accessType = accessType;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getClusterClass() {
        return clusterClass;
    }

    public void setClusterClass(String clusterClass) {
        this.clusterClass = clusterClass;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public String getSliceId() {
        return sliceId;
    }

    public void setSliceId(String sliceId) {
        this.sliceId = sliceId;
    }
}
