package com.baidubce.services.bes.v2.model.tag;

import com.baidubce.services.bes.v2.model.AbstractBesV2Request;
import lombok.Data;

import java.util.List;

/**
 * Request to update the tags of a single cluster.
 */
@Data
public class TagUpdateRequest extends AbstractBesV2Request {
    private String clusterId;
    private List<Tag> tags;
}
