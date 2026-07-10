package com.baidubce.services.eo.model.cache;

import com.baidubce.services.eo.model.EoResponse;

/**
 * The response of submitting cache prefetch task.
 */
public class PrefetchResponse extends EoResponse {
    /**
     * The id of cache prefetch task.
     */
    private String id;

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }
}
