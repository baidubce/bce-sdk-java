package com.baidubce.services.bcm.model.custom;

import lombok.Data;

/**
 * 自定义事件
 *
 * @Author: wanglu51
 * @Date: 2023/12/7 10:59
 */
@Data
public class NamespaceEvent {
    private String userId;
    private String namespace;
    private String eventName;
    private String eventNameAlias;
    private String eventLevel;
    private String comment;
}
