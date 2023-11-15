package com.baidubce.services.eiptp.model;

import com.baidubce.model.AbstractBceResponse;
import lombok.Getter;
import lombok.Setter;

/**
 * The response for getting eiptp detail.
 */
@Getter
@Setter
public class EipTpDetailResponse extends AbstractBceResponse {

    /**
     * The id of the eiptp.
     */
    private String id;

    /**
     * The deduct policy of the eiptp including "FullTimeDurationPackage" and "TimeDurationPackage".
     */
    private String deductPolicy;

    /**
     * The capacity of the eiptp.
     */
    private String capacity;

    /**
     * The capacity of the eiptp already used by the user.
     */
    private String usedCapacity;

    /**
     * The status of the eiptp including "RUNNING", "EXPIRED" and "USED_UP".
     */
    private String status;

    /**
     * The package type of the eiptp.
     */
    private String packageType;

    /**
     * The active time of the eiptp (GMT+8 format).
     */
    private String activeTime;

    /**
     * The expire time of the eiptp (GMT+8 format).
     */
    private String expireTime;

    /**
     * The eiptp createTime (GMT+8 format).
     */
    private String createTime;
}
