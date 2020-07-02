package com.baidubce.services.cdn.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.baidubce.util.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;

public class CdnRequest extends AbstractBceRequest {

    /**
     *  (non-Javadoc)
     * @see com.baidubce.model.AbstractBceRequest#withRequestCredentials(com.baidubce.auth.BceCredentials)
     */
    @Override
    public CdnRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    /**
     *  (non-Javadoc)
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
