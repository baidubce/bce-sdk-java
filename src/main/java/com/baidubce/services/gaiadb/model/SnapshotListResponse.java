package com.baidubce.services.gaiadb.model;

import com.baidubce.model.AbstractBceResponse;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.HashMap;
import java.util.Map;

/**
 * Response of listSnapshots. The underlying API may return different structures
 * depending on cluster type (e.g. distributed cluster vs. others), so all fields
 * are captured into a generic map via {@link JsonAnySetter}.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SnapshotListResponse extends AbstractBceResponse {

    private final Map<String, Object> data = new HashMap<String, Object>();

    @JsonAnySetter
    public void set(String key, Object value) {
        data.put(key, value);
    }

    @JsonAnyGetter
    public Map<String, Object> getData() {
        return data;
    }

    public Object get(String key) {
        return data.get(key);
    }
}
