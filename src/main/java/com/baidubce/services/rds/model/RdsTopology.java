package com.baidubce.services.rds.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Rds topology
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RdsTopology {

    private List<String> master = new ArrayList<String>();
    private List<String> readReplica = new ArrayList<String>();
    @JsonProperty("rdsproxy")
    private List<String> rdsProxy = new ArrayList<String>();
    private List<RdsReadReplicaIdMapping> readReplicaIdMapping = new ArrayList<RdsReadReplicaIdMapping>();

    public List<String> getMaster() {
        return master;
    }

    public void setMaster(List<String> master) {
        this.master = master;
    }

    public List<String> getReadReplica() {
        return readReplica;
    }

    public void setReadReplica(List<String> readReplica) {
        this.readReplica = readReplica;
    }

    public List<String> getRdsProxy() {
        return rdsProxy;
    }

    public void setRdsProxy(List<String> rdsProxy) {
        this.rdsProxy = rdsProxy;
    }

    public List<RdsReadReplicaIdMapping> getReadReplicaIdMapping() {
        return readReplicaIdMapping;
    }

    public void setReadReplicaIdMapping(List<RdsReadReplicaIdMapping> readReplicaIdMapping) {
        this.readReplicaIdMapping = readReplicaIdMapping;
    }
}
