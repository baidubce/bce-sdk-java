package com.baidubce.services.gaiadb.model;

public class OrderResponse extends GenericGaiadbResponse {
    private String orderId;
    private String uuid;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
