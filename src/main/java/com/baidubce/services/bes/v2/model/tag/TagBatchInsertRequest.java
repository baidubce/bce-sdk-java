package com.baidubce.services.bes.v2.model.tag;

import com.baidubce.services.bes.v2.model.AbstractBesV2Request;
import lombok.Data;

import java.util.List;

/**
 * Request to batch insert tags into multiple clusters.
 */
@Data
public class TagBatchInsertRequest extends AbstractBesV2Request {
    private List<String> clusterIdList;
    private List<Tag> insertTags;
}
