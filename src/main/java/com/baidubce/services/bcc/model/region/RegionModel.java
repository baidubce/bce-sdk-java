package com.baidubce.services.bcc.model.region;

import lombok.Data;

/**
 * The model of region's endpoint detail information
 */
@Data
public class RegionModel {
    /**
     * The id of region.
     */
    private String regionId;
    /**
     * The name of region.
     */
    private String regionName;
    /**
     * The endpoint of region.
     */
    private String regionEndpoint;
}
