package com.baidubce.services.cdn.model.certificate;

import com.baidubce.services.cdn.model.CdnRequest;

public class GetHttpsDomainRequest extends CdnRequest {

    /**
     * 指定certCommonName查询
     * 可选
     */
    private String certCommonName;

    /**
     * 指定certId查询
     * 可选
     */
    private String certId;

    /**
     * 指定certName查询
     * 可选
     */
    private String certName;

    /**
     * 查询指定域名。必须属于当前用户，只支持精确匹配
     * 可选
     */
    private String domain;

    /**
     * 分页参数，第几页
     * 可选
     */
    private Integer pageNo;

    /**
     * 分页参数，页面大小。1 <= pageSize <= 100
     * 可选
     */
    private Integer pageSize;

    /**
     * 指定证书状态查询
     * 可选
     */
    private Integer status;


    public GetHttpsDomainRequest() {
    }

    public GetHttpsDomainRequest(Integer pageNo, Integer pageSize) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    public GetHttpsDomainRequest(Integer pageNo, Integer pageSize, Integer status) {
        this(pageNo, pageSize);
        this.status = status;
    }

    public GetHttpsDomainRequest withCertId(String certId) {
        this.certId = certId;
        return this;
    }

    public GetHttpsDomainRequest withCertCommonName(String certCommonName) {
        this.certCommonName = certCommonName;
        return this;
    }

    public GetHttpsDomainRequest withCertName(String certName) {
        this.certName = certName;
        return this;
    }

    public GetHttpsDomainRequest withDomain(String domain) {
        this.domain = domain;
        return this;
    }

    public GetHttpsDomainRequest withPageNo(Integer pageNo) {
        this.pageNo = pageNo;
        return this;
    }

    public GetHttpsDomainRequest withPageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public GetHttpsDomainRequest withStatus(Integer status) {
        this.status = status;
        return this;
    }


    public String getCertCommonName() {
        return certCommonName;
    }

    public void setCertCommonName(String certCommonName) {
        this.certCommonName = certCommonName;
    }

    public String getCertId() {
        return certId;
    }

    public void setCertId(String certId) {
        this.certId = certId;
    }

    public String getCertName() {
        return certName;
    }

    public void setCertName(String certName) {
        this.certName = certName;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
