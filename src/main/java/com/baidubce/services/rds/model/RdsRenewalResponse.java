package com.baidubce.services.rds.model;

import com.baidubce.model.AbstractBceResponse;
import org.apache.htrace.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The Response of Renewal instance
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RdsRenewalResponse extends AbstractBceResponse {

    private String orderId;
    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

}
