package com.baidubce.services.rds.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Rds topology
 */
public class RdsTopology {

    private List<String> master = new ArrayList<String>();
    private List<String> readReplica = new ArrayList<String>();
    @JsonProperty("rdsproxy")
    private List<String> rdsProxy = new ArrayList<String>();
    private List<RdsTopologyReadReplica> readReplicaIdMapping = new ArrayList<RdsTopologyReadReplica>();

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

    public List<RdsTopologyReadReplica> getReadReplicaIdMapping() {
        return readReplicaIdMapping;
    }

    public void setReadReplicaIdMapping(List<RdsTopologyReadReplica> readReplicaIdMapping) {
        this.readReplicaIdMapping = readReplicaIdMapping;
    }
}
