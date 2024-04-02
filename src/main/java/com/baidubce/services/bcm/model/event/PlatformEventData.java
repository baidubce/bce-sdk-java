package com.baidubce.services.bcm.model.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dongjiawei on 2023/12/12.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlatformEventData {

    /**
     * 用户ID
     */
    private String userId;
    /**
     * 事件来源
     */
    private String eventSource;
    /**
     * 事件名称
     */
    private String eventName;
    /**
     * 事件发生的时间戳，UTC格式
     */
    private String timestamp;
    /**
     * 事件ID，用于标识一个事件，事件ID在租户、应用、区域下唯一。ID由业务方生成。
     */
    private String eventId;
    /**
     * 事件归属的区域名
     */
    private String region;
    /**
     * 事件归属的区域名
     */
    private String az;
    /**
     * 事件级别，[NOTICE/WARNING/MAJOR/CRITICAL]，非空会覆盖默认值
     */
    private EventLevel eventLevel;
    /**
     * 事件别名，非空会覆盖默认值
     */
    private String eventAlias;
    /**
     * 父类型的事件名称
     */
    private String eventParentAlias;
    /**
     * 事件内容
     */
    private String content;

    /*事件影响范围*/
    private List<PlatformResource> resources = new ArrayList<PlatformResource>();

}
