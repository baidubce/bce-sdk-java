package com.baidubce.services.geo.model;

import com.baidubce.util.JsonUtils;

import com.fasterxml.jackson.core.JsonProcessingException;

public class JsonObject {
    /**
     * (non-Javadoc)
     * @see Object#toString()
     */
    public String toString() {
        try {
            return JsonUtils.toJsonPrettyString(this);
        } catch (JsonProcessingException e) {
            return "";
        }
    }
}
