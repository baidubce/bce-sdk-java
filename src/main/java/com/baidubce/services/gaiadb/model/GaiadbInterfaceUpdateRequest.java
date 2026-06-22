package com.baidubce.services.gaiadb.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class GaiadbInterfaceUpdateRequest extends GenericGaiadbRequest {
    private String clusterId;
    private String interfaceId;
    @JsonProperty("interface")
    private InterfaceConfig interfacee;

    public String getClusterId() {
        return clusterId;
    }

    public void setClusterId(String clusterId) {
        this.clusterId = clusterId;
    }

    public String getInterfaceId() {
        return interfaceId;
    }

    public void setInterfaceId(String interfaceId) {
        this.interfaceId = interfaceId;
    }

    public InterfaceConfig getInterfacee() {
        return interfacee;
    }

    public void setInterfacee(InterfaceConfig interfacee) {
        this.interfacee = interfacee;
    }

    public static class InterfaceConfig {
        private String addressName;
        private List<String> instanceBinding;
        private Integer masterReadable;
        private Integer globalConsistency;
        private Long consistTimeout;
        private Integer consistTimeoutAction;
        private List<Integer> weights;
        private String connectionPersist;

        public String getAddressName() {
            return addressName;
        }

        public void setAddressName(String addressName) {
            this.addressName = addressName;
        }

        public List<String> getInstanceBinding() {
            return instanceBinding;
        }

        public void setInstanceBinding(List<String> instanceBinding) {
            this.instanceBinding = instanceBinding;
        }

        public Integer getMasterReadable() {
            return masterReadable;
        }

        public void setMasterReadable(Integer masterReadable) {
            this.masterReadable = masterReadable;
        }

        public Integer getGlobalConsistency() {
            return globalConsistency;
        }

        public void setGlobalConsistency(Integer globalConsistency) {
            this.globalConsistency = globalConsistency;
        }

        public Long getConsistTimeout() {
            return consistTimeout;
        }

        public void setConsistTimeout(Long consistTimeout) {
            this.consistTimeout = consistTimeout;
        }

        public Integer getConsistTimeoutAction() {
            return consistTimeoutAction;
        }

        public void setConsistTimeoutAction(Integer consistTimeoutAction) {
            this.consistTimeoutAction = consistTimeoutAction;
        }

        public List<Integer> getWeights() {
            return weights;
        }

        public void setWeights(List<Integer> weights) {
            this.weights = weights;
        }

        public String getConnectionPersist() {
            return connectionPersist;
        }

        public void setConnectionPersist(String connectionPersist) {
            this.connectionPersist = connectionPersist;
        }
    }
}
