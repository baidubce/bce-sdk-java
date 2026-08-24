package com.baidubce.services.bes.v2.model.config;

import com.baidubce.services.bes.v2.model.AbstractBesV2Request;
import lombok.Data;

/**
 * Request to delete a synonym dict.
 */
@Data
public class SynonymDictDeleteRequest extends AbstractBesV2Request {
    private String clusterId;
    private String synonymDictName;
}
