package com.baidubce.services.eo.model.stat;

import com.baidubce.services.eo.model.JsonObject;

/**
 * The limit condition of a stat metric query, usually used for paging or limiting the count.
 */
public class LimitField extends JsonObject {
    /**
     * How many records to return. Mainly used for top data, 100 is recommended.
     */
    private Integer pageSize;

    /**
     * @param pageSize
     * @return this object
     */
    public LimitField withPageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    /**
     * @return pageSize
     */
    public Integer getPageSize() {
        return pageSize;
    }

    /**
     * @param pageSize
     */
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
