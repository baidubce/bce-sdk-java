package com.baidubce.services.bes.v2.model.inspect;

import com.baidubce.services.bes.v2.model.AbstractBesV2Request;
import lombok.Data;

/**
 * Request to get the execution status and result of an inspection task.
 */
@Data
public class InspectTaskGetRequest extends AbstractBesV2Request {
    private String clusterId;
    private String taskId;
}
