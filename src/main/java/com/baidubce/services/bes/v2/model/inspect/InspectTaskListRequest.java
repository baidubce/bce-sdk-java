package com.baidubce.services.bes.v2.model.inspect;

import com.baidubce.services.bes.v2.model.AbstractBesV2Request;
import lombok.Data;

/**
 * Request to list inspection tasks completed in the past 7 days.
 */
@Data
public class InspectTaskListRequest extends AbstractBesV2Request {
    private String clusterId;
    private Integer pageNo;
    private Integer pageSize;
}
