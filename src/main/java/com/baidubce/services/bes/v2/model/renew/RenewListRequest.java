package com.baidubce.services.bes.v2.model.renew;

import com.baidubce.services.bes.v2.model.AbstractBesV2Request;
import lombok.Data;

/**
 * Request to list renewals.
 */
@Data
public class RenewListRequest extends AbstractBesV2Request {
    private String order;
    private String orderBy;
    private Integer pageNo;
    private Integer pageSize;
    private Integer daysToExpiration;
}
