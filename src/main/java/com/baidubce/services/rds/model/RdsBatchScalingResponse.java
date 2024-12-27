package com.baidubce.services.rds.model;

import com.baidubce.model.AbstractBceResponse;
import org.apache.htrace.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * The Response of Batch scaling  instances
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RdsBatchScalingResponse extends AbstractBceResponse {
    private List<String> orderList;

    public List<String> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<String> orderList) {
        this.orderList = orderList;
    }
}
