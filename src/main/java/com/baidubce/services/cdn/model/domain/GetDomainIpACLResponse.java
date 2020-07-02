package com.baidubce.services.cdn.model.domain;

import com.baidubce.services.cdn.model.CdnResponse;
import com.baidubce.services.cdn.model.IpACL;

/**
 * create by changxing01 on 19/9/3
 */
public class GetDomainIpACLResponse extends CdnResponse {
    private IpACL ipACL;

    /**
     * @return ipACL
     */
    public IpACL getIpACL() {
        return ipACL;
    }

    /**
     * @param ipACL ip config info
     */
    public void setIpACL(IpACL ipACL) {
        this.ipACL = ipACL;
    }
}
