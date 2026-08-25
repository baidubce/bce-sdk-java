package com.baidubce.services.bes.v2.model.logmanage;

import com.baidubce.services.bes.v2.model.AbstractBesV2Request;
import lombok.Data;

/**
 * Request to search logs.
 */
@Data
public class LogSearchRequest extends AbstractBesV2Request {
    private String clusterId;
    private Integer pageNo;
    private Integer pageSize;
    private String keyword;
    private String startTime;
    private String endTime;
    private String type;
}
