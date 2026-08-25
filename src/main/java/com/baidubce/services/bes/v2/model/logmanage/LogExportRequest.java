package com.baidubce.services.bes.v2.model.logmanage;

import com.baidubce.services.bes.v2.model.AbstractBesV2Request;
import lombok.Data;

import java.util.List;

/**
 * Request to create a log export task.
 */
@Data
public class LogExportRequest extends AbstractBesV2Request {
    private String clusterId;
    private String startTime;
    private String endTime;
    private List<String> types;
    private String bosBucket;
    private String bosPath;
}
