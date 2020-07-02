package com.baidubce.services.cdn.model.domain;

import com.baidubce.services.cdn.model.CdnResponse;

import java.util.List;

/**
 * create by changxing01 on 19/8/27
 */
public class GetUserDomainResponse extends CdnResponse {
    private List<UserDomain> domains;

    /**
     * @return domain list
     */
    public List<UserDomain> getDomains() {
        return domains;
    }

    /**
     * @param domains user's domains
     */
    public void setDomains(List<UserDomain> domains) {
        this.domains = domains;
    }
}
