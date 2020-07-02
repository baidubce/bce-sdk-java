package com.baidubce.services.cdn.model.domain;

import com.baidubce.services.cdn.model.CdnResponse;

import java.util.List;

public class GetDomainErrorPageResponse extends CdnResponse {
    private List<ErrorPage> errorPage;

    /**
     * @return errorPage
     */
    public List<ErrorPage> getErrorPage() {
        return errorPage;
    }

    /**
     * @param errorPage error page info
     */
    public void setErrorPage(List<ErrorPage> errorPage) {
        this.errorPage = errorPage;
    }

}
