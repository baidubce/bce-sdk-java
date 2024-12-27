package com.baidubce.services.bcc.model.reversed;

import com.baidubce.model.AbstractBceResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
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
public class DescribeReservedInstancesResponse extends AbstractBceResponse {

    @JsonProperty("totalCount")
    private Integer totalCount;

    @JsonProperty("reservedInstances")
    private List<ReservedInstanceDetail> reservedInstances;

}
