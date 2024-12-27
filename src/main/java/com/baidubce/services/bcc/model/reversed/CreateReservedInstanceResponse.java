package com.baidubce.services.bcc.model.reversed;

import com.baidubce.model.AbstractBceResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: lilu24
 * @Date: 2024-06-20
 */


@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateReservedInstanceResponse extends AbstractBceResponse {

    private List<String> reservedInstanceIds;

    private String orderId;
}
