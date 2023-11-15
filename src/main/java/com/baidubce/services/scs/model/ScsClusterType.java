package com.baidubce.services.scs.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Scs cluster type
 */
public enum ScsClusterType {
    /**
     * cluster 企业集群版
     */
    CLUSTER("cluster"),

    /**
     * master_slave 主从版
     */
    MASTER_SLAVE("master_slave"),

    /**
     * native-cluster 社区版集群
     */
    NATIVE_CLUSTER("native-cluster"),
    /**
     * unknown type
     */
    UNKNOWN("unknown");

    private final String value;

    ScsClusterType(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }

    @JsonCreator
    public static ScsClusterType create(String value) {
        if (CLUSTER.value.equalsIgnoreCase(value)) {
            return CLUSTER;
        } else if (MASTER_SLAVE.value.equalsIgnoreCase(value)) {
            return MASTER_SLAVE;
        } else if (NATIVE_CLUSTER.value.equalsIgnoreCase(value)) {
            return NATIVE_CLUSTER;
        } else {
            return UNKNOWN;
        }
    }
}
