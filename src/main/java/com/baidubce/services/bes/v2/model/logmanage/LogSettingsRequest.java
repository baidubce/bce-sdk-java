package com.baidubce.services.bes.v2.model.logmanage;

import com.baidubce.services.bes.v2.model.AbstractBesV2Request;
import lombok.Data;

/**
 * Request to update log settings.
 */
@Data
public class LogSettingsRequest extends AbstractBesV2Request {
    private String clusterId;
    private Boolean indexSlowLog;
    private Boolean searchSlowLog;
    private Boolean auditLog;
}
