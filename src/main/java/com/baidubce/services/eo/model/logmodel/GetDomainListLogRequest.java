package com.baidubce.services.eo.model.logmodel;

import com.baidubce.services.eo.model.EoRequest;

import java.util.List;

public class GetDomainListLogRequest extends EoRequest {
    private String site;
    private String startTime;
    private String endTime;
    private List<String> domainList;
    private Integer pageNo;
    private Integer pageSize;

    /**
     * @param site
     * @return this object
     */
    public GetDomainListLogRequest withSite(String site) {
        this.site = site;
        return this;
    }

    /**
     * @return site
     */
    public String getSite() {
        return site;
    }

    /**
     * @param site
     */
    public void setSite(String site) {
        this.site = site;
    }

    /**
     * @param startTime
     * @return this object
     */
    public GetDomainListLogRequest withStartTime(String startTime) {
        this.startTime = startTime;
        return this;
    }

    /**
     * @return startTime
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * @param startTime
     */
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    /**
     * @param endTime
     * @return this object
     */
    public GetDomainListLogRequest withEndTime(String endTime) {
        this.endTime = endTime;
        return this;
    }

    /**
     * @return endTime
     */
    public String getEndTime() {
        return endTime;
    }

    /**
     * @param endTime
     */
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    /**
     * @param domainList
     * @return this object
     */
    public GetDomainListLogRequest withDomainList(List<String> domainList) {
        this.domainList = domainList;
        return this;
    }

    /**
     * @return domainList
     */
    public List<String> getDomainList() {
        return domainList;
    }

    /**
     * @param domainList
     */
    public void setDomainList(List<String> domainList) {
        this.domainList = domainList;
    }

    /**
     * @param pageNo
     * @return this object
     */
    public GetDomainListLogRequest withPageNo(Integer pageNo) {
        this.pageNo = pageNo;
        return this;
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
     * @param pageSize
     * @return this object
     */
    public GetDomainListLogRequest withPageSize(Integer pageSize) {
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
