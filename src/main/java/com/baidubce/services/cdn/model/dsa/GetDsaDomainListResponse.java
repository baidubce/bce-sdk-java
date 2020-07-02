package com.baidubce.services.cdn.model.dsa;

import com.baidubce.services.cdn.model.CdnResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * create by changxing01 on 19/8/28
 */
public class GetDsaDomainListResponse extends CdnResponse {
    private List<DSADomain> domains;

    /**
     * @return domains
     */
    public List<DSADomain> getDomains() {
        return domains;
    }

    /**
     * @param domains domain rule detail
     */
    public void setDomains(List<DSADomain> domains) {
        this.domains = domains;
    }

    /**
     * @param domains domain rule detail list
     * @return this object
     */
    public GetDsaDomainListResponse withDomains(List<DSADomain> domains) {
        this.domains = domains;
        return this;
    }

    /**
     * @param domain domain rule detail
     * @return this object
     */
    public GetDsaDomainListResponse addDomains(DSADomain domain) {
        if (this.domains == null) {
            this.domains = new ArrayList<DSADomain>();
        }
        this.domains.add(domain);
        return this;
    }
}
