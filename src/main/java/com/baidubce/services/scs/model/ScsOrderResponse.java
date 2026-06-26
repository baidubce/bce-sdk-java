package com.baidubce.services.scs.model;

import com.baidubce.model.AbstractBceResponse;

/**
 * Response containing order id.
 */
public class ScsOrderResponse extends AbstractBceResponse {

    private String orderId;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
