package com.baidubce.services.cdn.model.domain;

import com.baidubce.services.cdn.model.CdnResponse;

import java.util.List;

/**
 * create by changxing01 on 19/8/28
 */
public class GetDomainHttpHeaderResponse extends CdnResponse {

    private List<HttpHeader> httpHeader;

    /**
     * @return httpHeader
     */
    public List<HttpHeader> getHttpHeader() {
        return httpHeader;
    }

    /**
     * @param httpHeader Set back source or respond to Header
     */
    public void setHttpHeader(List<HttpHeader> httpHeader) {
        this.httpHeader = httpHeader;
    }
}
