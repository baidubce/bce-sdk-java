package com.baidubce.services.bcm.model.custom;

import lombok.AllArgsConstructor;

/**
 * 事件级别枚举
 *
 * @Author: wanglu51
 * @Date: 2023/12/7 18:45
 */
@AllArgsConstructor
public enum EventLevelEnum {
    /**
     * 通知事件级别
     */
    NOTICE,

    /**
     * 预警事件级别
     */
    MAJOR,

    /**
     * 警告事件级别
     */
    WARNING,

    /**
     * 故障事件级别
     */
    CRITICAL,

    ;
}
