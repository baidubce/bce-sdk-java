package com.baidubce.services.mongodb.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Response of adding a component to a sharded cluster.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MongodbCreateNodeResponse extends GenericMongodbResponse {

    private String orderId;

    private List<String> nodeIds;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public List<String> getNodeIds() {
        return nodeIds;
    }

    public void setNodeIds(List<String> nodeIds) {
        this.nodeIds = nodeIds;
    }
}
