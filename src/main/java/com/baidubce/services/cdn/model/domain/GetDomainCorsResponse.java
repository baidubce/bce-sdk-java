package com.baidubce.services.cdn.model.domain;

import com.baidubce.services.cdn.model.CdnResponse;

public class GetDomainCorsResponse extends CdnResponse {
    private Cors cors;

    /**
     * @return cors
     */
    public Cors getCors() {
        return cors;
    }

    /**
     * @param cors the cors config of domain
     */
    public void setCors(Cors cors) {
        this.cors = cors;
    }
}
