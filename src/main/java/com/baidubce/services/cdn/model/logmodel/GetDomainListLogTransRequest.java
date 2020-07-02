package com.baidubce.services.cdn.model.logmodel;

import com.baidubce.services.cdn.model.CdnRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * create by changxing01 on 19/8/28
 */
public class GetDomainListLogTransRequest extends CdnRequest {
    private String startTime;
    private String endTime;
    private Long type;
    private List<String> domains;
    private int pageNo;
    private int pageSize;

    /**
     * @return startTime
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * @param startTime
     * @return this object
     */
    public GetDomainListLogTransRequest withStartTime(String startTime) {
        this.startTime = startTime;
        return this;
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
     * @param endTime
     * @return this object
     */
    public GetDomainListLogTransRequest withEndTime(String endTime) {
        this.endTime = endTime;
        return this;
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
     * @param type
     * @return this object
     */
    public GetDomainListLogTransRequest withType(Long type) {
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
    public GetDomainListLogTransRequest withDomains(List<String> domains) {
        this.domains = domains;
        return this;
    }

    /**
     * @param domain domain name
     * @return this object
     */
    public GetDomainListLogTransRequest addDomains(String domain) {
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
     * @param pageNo
     * @return this object
     */
    public GetDomainListLogTransRequest withPageNo(int pageNo) {
        this.pageNo = pageNo;
        return this;
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

    /**
     * @param pageSize
     * @return this object
     */
    public GetDomainListLogTransRequest withPageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }
}
