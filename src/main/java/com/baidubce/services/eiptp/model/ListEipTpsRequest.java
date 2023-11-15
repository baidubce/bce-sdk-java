package com.baidubce.services.eiptp.model;

import com.baidubce.services.eip.model.ListRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
/**
 * The request for listing eiptp.
 */
public class ListEipTpsRequest extends ListRequest {

    /**
     * The eiptp id.
     * The optional parameter.
     */
    private String id;

    /**
     * The deduct policy of the eiptp including "FullTimeDurationPackage" and "TimeDurationPackage".
     * The optional parameter.
     */
    private String deductPolicy;

    /**
     * The status of the eiptp including "RUNNING", "EXPIRED" and "USED_UP".
     * The optional parameter.
     */
    private String status;
}
