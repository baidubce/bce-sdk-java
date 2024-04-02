package com.baidubce.services.bcm.model.group;

/**
 * Created by dongjiawei on 2023/12/12.
 */
public enum IGInstanceQueryType {
    /**
     * ALL: return all instance
     */
    ALL,
    /**
     * FILTER: Returns instances that are not included in the instance group
     */
    FILTER,
    /**
     * INCLUDE: Returns instances that are included in the instance group
     */
    INCLUDE,
}
