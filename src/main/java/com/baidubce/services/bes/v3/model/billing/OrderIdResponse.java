package com.baidubce.services.bes.v3.model.billing;

import com.baidubce.model.AbstractBceResponse;

import lombok.Data;

@Data
public class OrderIdResponse extends AbstractBceResponse {
    private String orderId;
}
