package com.baidubce.services.mongodb.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Generic order response carrying an order id.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MongodbOrderResponse extends GenericMongodbResponse {
    private String orderId;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
