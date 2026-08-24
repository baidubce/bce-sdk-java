/*
 * Copyright (C) 2026 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.cas.model.response;

import com.baidubce.model.AbstractBceResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Response of creating a certificate order.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class OrderResponse extends AbstractBceResponse {
    private String bceOrderId;
    private String orderId;

    public OrderResponse(String bceOrderId) {
        this.bceOrderId = bceOrderId;
        this.orderId = bceOrderId;
    }
}
