package com.baidubce.services.bes.v2.model.cluster;

import com.baidubce.services.bes.v2.model.AbstractBesV2Request;
import lombok.Data;

/**
 * Request to reset cluster password.
 */
@Data
public class ClusterPasswordRequest extends AbstractBesV2Request {
    private String clusterId;
    private String newPassword;
    private String confirmPassword;
}
