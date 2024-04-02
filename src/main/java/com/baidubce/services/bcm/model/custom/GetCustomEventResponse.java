package com.baidubce.services.bcm.model.custom;

import com.baidubce.model.AbstractBceResponse;
import lombok.Data;

/**
 * 获取某个自定义空间事件响应
 *
 * @Author: wanglu51
 * @Date: 2023/12/7 10:59
 */
@Data
public class GetCustomEventResponse extends AbstractBceResponse {
    private String userId;
    private String namespace;
    private String eventName;
    private String eventNameAlias;
    private String eventLevel;
    private String comment;
}
