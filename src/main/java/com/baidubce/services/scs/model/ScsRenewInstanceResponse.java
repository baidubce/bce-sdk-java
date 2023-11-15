package com.baidubce.services.scs.model;

import com.baidubce.model.AbstractBceResponse;

/**
 * The response of renew instance which has a order id
 */
public class ScsRenewInstanceResponse extends AbstractBceResponse {

    private String orderId;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
