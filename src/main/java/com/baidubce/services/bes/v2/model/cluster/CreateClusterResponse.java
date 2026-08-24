package com.baidubce.services.bes.v2.model.cluster;

import com.baidubce.model.AbstractBceResponse;
import lombok.Data;

/**
 * Response for create cluster.
 */
@Data
public class CreateClusterResponse extends AbstractBceResponse {
    private Boolean success;
    private Integer status;
    private Object result;

}
