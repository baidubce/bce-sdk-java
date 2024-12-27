package com.baidubce.services.bcc.model.reversed;

import com.baidubce.model.AbstractBceResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author xubinghao
 * @date 2023-02-22
 */

/**
 * @Author: lilu24
 * @Date: 2024-06-20
 */

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ModifyReservedInstancesResponse extends AbstractBceResponse {

    private List<ModifyReservedInstanceOrder> modifyReservedInstanceOrders;

    @JsonIgnoreProperties(ignoreUnknown = true)
    @NoArgsConstructor
    @Data
    public static class ModifyReservedInstanceOrder {

        private String reservedInstanceId;

        private String orderId;

        private boolean success;

        private String exception;

    }
}
