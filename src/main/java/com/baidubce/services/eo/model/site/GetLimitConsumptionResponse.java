package com.baidubce.services.eo.model.site;

import com.baidubce.services.eo.model.EoResponse;

import java.util.List;

/**
 * The response of querying consumption limit rules of a site.
 */
public class GetLimitConsumptionResponse extends EoResponse {
    /**
     * The consumption limit rule list.
     */
    private List<LimitConsumptionRule> rules;

    /**
     * The total number of rules, used for paging.
     */
    private Integer totalCount;

    /**
     * The page number, defaults to 1.
     */
    private Integer pageNo;

    /**
     * The page size, defaults to 10.
     */
    private Integer pageSize;

    /**
     * All target objects. "site" means the queried site, others are domains under the site.
     */
    private List<String> allObjectList;

    /**
     * @return rules
     */
    public List<LimitConsumptionRule> getRules() {
        return rules;
    }

    /**
     * @param rules
     */
    public void setRules(List<LimitConsumptionRule> rules) {
        this.rules = rules;
    }

    /**
     * @return totalCount
     */
    public Integer getTotalCount() {
        return totalCount;
    }

    /**
     * @param totalCount
     */
    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    /**
     * @return pageNo
     */
    public Integer getPageNo() {
        return pageNo;
    }

    /**
     * @param pageNo
     */
    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
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

    /**
     * @return allObjectList
     */
    public List<String> getAllObjectList() {
        return allObjectList;
    }

    /**
     * @param allObjectList
     */
    public void setAllObjectList(List<String> allObjectList) {
        this.allObjectList = allObjectList;
    }
}
