package com.baidubce.services.cdn.model.util;

import com.baidubce.services.cdn.model.CdnRequest;

public class GetForbiddenUrlsRequest extends CdnRequest {

    /**
     * 分页查询页面大小，默认值为10，最大不超过100
     */
    private int pageSize = 10;
    private int pageNo = 1;

    /**
     * 值为desc或asc，指定时间倒序输出还是时间顺序输出。默认为desc，时序倒序输出。设置为asc时按时序顺序输出
     */
    private String orderBy = "desc";

    /**
     * url path不能超过1024字符
     * 可选
     */
    private String url;

    public GetForbiddenUrlsRequest() {
    }

    public GetForbiddenUrlsRequest(String url) {
        this.url = url;
    }

    public GetForbiddenUrlsRequest withPageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public GetForbiddenUrlsRequest withPageNo(int pageNo) {
        this.pageNo = pageNo;
        return this;
    }

    public GetForbiddenUrlsRequest withUrl(String url) {
        this.url = url;
        return this;
    }

    public GetForbiddenUrlsRequest withOrderBy(String orderBy) {
        this.orderBy = orderBy;
        return this;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
