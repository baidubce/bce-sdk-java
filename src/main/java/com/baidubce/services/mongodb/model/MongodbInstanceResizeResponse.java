package com.baidubce.services.mongodb.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * Response of resizing a MongoDB instance.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MongodbInstanceResizeResponse extends GenericMongodbResponse {
    private String orderId;

    private List<String> readonlyMemberIds = new ArrayList<String>();

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public List<String> getReadonlyMemberIds() {
        return readonlyMemberIds;
    }

    public void setReadonlyMemberIds(List<String> readonlyMemberIds) {
        this.readonlyMemberIds = readonlyMemberIds;
    }
}
