package com.baidubce.services.bes.v2.model.logmanage;

import com.baidubce.services.bes.v2.model.AbstractBesV2Request;
import lombok.Data;

/**
 * Request to get log export task record.
 */
@Data
public class LogExportRecordRequest extends AbstractBesV2Request {
    private String clusterId;
    private String taskId;
}
