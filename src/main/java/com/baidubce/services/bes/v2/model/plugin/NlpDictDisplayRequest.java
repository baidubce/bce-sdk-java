package com.baidubce.services.bes.v2.model.plugin;

import com.baidubce.services.bes.v2.model.AbstractBesV2Request;
import lombok.Data;

/**
 * Request to view the nlp dict.
 */
@Data
public class NlpDictDisplayRequest extends AbstractBesV2Request {
    private String clusterId;
}
