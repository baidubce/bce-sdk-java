package com.baidubce.services.geo.model;

import com.baidubce.BceResponseMetadata;
import com.baidubce.model.AbstractBceResponse;
import com.baidubce.util.JsonUtils;

import com.fasterxml.jackson.core.JsonProcessingException;

public class GeoResponse extends AbstractBceResponse {
    /**
     * @param metadata
     */
    public void setMetadata(BceResponseMetadata metadata) {
        this.metadata = metadata;
    }

    /**
     * (non-Javadoc)
     * @see Object#toString()
     */
    @Override
    public String toString() {
        try {
            return JsonUtils.toJsonPrettyString(this);
        } catch (JsonProcessingException e) {
            return "";
        }
    }
}
