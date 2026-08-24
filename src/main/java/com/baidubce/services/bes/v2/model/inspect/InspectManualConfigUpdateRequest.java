package com.baidubce.services.bes.v2.model.inspect;

import com.baidubce.services.bes.v2.model.AbstractBesV2Request;
import lombok.Data;

import java.util.List;

/**
 * Request to update the manual inspection task config.
 */
@Data
public class InspectManualConfigUpdateRequest extends AbstractBesV2Request {
    private String clusterId;
    private String indices;
    private List<String> items;
}
