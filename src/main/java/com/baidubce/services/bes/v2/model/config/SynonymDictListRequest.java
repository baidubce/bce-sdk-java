package com.baidubce.services.bes.v2.model.config;

import com.baidubce.services.bes.v2.model.AbstractBesV2Request;
import lombok.Data;

/**
 * Request to list synonym dicts.
 */
@Data
public class SynonymDictListRequest extends AbstractBesV2Request {
    private String clusterId;
}
