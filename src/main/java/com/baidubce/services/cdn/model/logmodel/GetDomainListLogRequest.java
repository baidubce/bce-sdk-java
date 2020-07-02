package com.baidubce.services.cdn.model.logmodel;

import com.baidubce.services.cdn.model.CdnRequest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * create by changxing01 on 19/8/28
 */
public class GetDomainListLogRequest extends CdnRequest {
    private Date startTime;
    private Date endTime;
    private Long type;
    private List<String> domains;
    private int pageNo;
    private int pageSize;

    /**
     * @param startTime
     */
    public GetDomainListLogRequest withStartTime(Date startTime) {
        this.startTime = startTime;
        return this;
    }

    /**
     * @param endTime
     */
    public GetDomainListLogRequest withEndTime(Date endTime) {
        this.endTime = endTime;
        return this;
    }

    /**
     * @return startTime
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * @param startTime
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * @return endTime
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * @param endTime
     */
    public void setEndTime(Date endTime) {
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


    /**
     * @return pageNo
     */
    public int getPageNo() {
        return pageNo;
    }

    /**
     * @param pageNo
     */
    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    /**
     * @return pageSize
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * @param pageSize
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
