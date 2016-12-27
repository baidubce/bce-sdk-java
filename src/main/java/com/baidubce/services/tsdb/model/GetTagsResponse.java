package com.baidubce.services.tsdb.model;

import java.util.List;
import java.util.Map;

import com.baidubce.model.AbstractBceResponse;

/**
 * Represent the response for getting tags from Tsdb.
 */
public class GetTagsResponse extends AbstractBceResponse {
    
    private Map<String, List<String>> tags;

    public Map<String, List<String>> getTags() {
        return tags;
    }

    public void setTags(Map<String, List<String>> tags) {
        this.tags = tags;
    }
}
