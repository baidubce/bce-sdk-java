package com.baidubce.services.bcm.model.custom;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 自定义事件请求
 *
 * @Author: wanglu51
 * @Date: 2023/12/7 10:59
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NamespaceEventRequest extends AbstractBceRequest {
    private String userId;
    private String namespace;
    private String eventName;
    private String eventNameAlias;
    private EventLevelEnum eventLevel;
    private String comment;

    @Override
    public NamespaceEventRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
