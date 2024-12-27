package com.baidubce.services.vpc.model;

import java.util.List;

import com.baidubce.model.AbstractBceResponse;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * The response for GetVpcResourceIp.
 */
@Getter
@Setter
@ToString
public class GetVpcResourceIpResponse extends AbstractBceResponse {
    /**
     * List of vpc resource ip info
     */
    private List<VpcResourceIp> result;

    /**
     * The page number of list.
     */
    private Integer pageNo;

    /**
     * The page size of list.
     */
    private Integer pageSize;

    /**
     * Total number of results.
     */
    private Integer totalCount;
}
