package com.baidubce.services.bes.v2.model.inspect;

import com.baidubce.services.bes.v2.model.AbstractBesV2Request;
import lombok.Data;

/**
 * Request to switch auto inspection on or off.
 */
@Data
public class InspectAutoSwitchRequest extends AbstractBesV2Request {
    private String clusterId;
    private Boolean switchOn;
}
