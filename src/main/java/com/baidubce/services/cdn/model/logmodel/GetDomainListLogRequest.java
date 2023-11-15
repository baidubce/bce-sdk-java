package com.baidubce.services.cdn.model.logmodel;

import com.baidubce.services.cdn.model.CdnRequest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * create by changxing01 on 19/8/28
 */
public class GetDomainListLogRequest extends CdnRequest {
    private String startTime;
    private String endTime;
    private Long type;
    private List<String> domains;
    private Integer pageNo;
    private Integer pageSize;

    /**
     * @param startTime
     */
    public GetDomainListLogRequest withStartTime(String startTime) {
        this.startTime = startTime;
        return this;
    }

    /**
     * @param endTime
     */
    public GetDomainListLogRequest withEndTime(String endTime) {
        this.endTime = endTime;
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
     * @return type
     */
    public Long getType() {
        return type;
    }

    /**
     * @param type logmodel type
     */
    public void setType(Long type) {
        this.type = type;
    }

    public GetDomainListLogRequest withType(Long type) {
        this.type = type;
        return this;
    }

    /**
     * @return domains
     */
    public List<String> getDomains() {
        return domains;
    }

    /**
     * @param domains domain list
     */
    public void setDomains(List<String> domains) {
        this.domains = domains;
    }

    /**
     * @param domains domain list
     * @return this object
     */
    public GetDomainListLogRequest withDomains(List<String> domains) {
        this.domains = domains;
        return this;
    }

    /**
     * @param domain domain name
     * @return this object
     */
    public GetDomainListLogRequest addDomains(String domain) {
        if (this.domains == null) {
            this.domains = new ArrayList<String>();
        }
        this.domains.add(domain);
        return this;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public GetDomainListLogRequest withPageNo(Integer pageNo) {
        this.pageNo = pageNo;
        return this;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public GetDomainListLogRequest withPageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }
}
