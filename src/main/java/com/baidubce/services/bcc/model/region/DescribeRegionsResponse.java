package com.baidubce.services.bcc.model.region;

import com.baidubce.model.AbstractBceResponse;
import lombok.Data;

import java.util.List;

/**
 * The response for DescribeRegionsRequest.
 */
@Data
public class DescribeRegionsResponse extends AbstractBceResponse {
    /**
     * The list of the model which is region's endpoint detail information.
     */
    private List<RegionModel> regions;
}
