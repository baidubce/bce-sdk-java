package com.baidubce.services.eo.model.cache;

import com.baidubce.services.eo.model.EoResponse;

/**
 * The response of submitting cache purge task.
 */
public class PurgeResponse extends EoResponse {
    /**
     * The id of cache purge task.
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
