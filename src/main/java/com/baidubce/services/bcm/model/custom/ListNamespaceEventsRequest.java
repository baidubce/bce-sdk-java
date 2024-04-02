package com.baidubce.services.bcm.model.custom;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 获取自定义空间事件列表请求
 *
 * @Author: wanglu51
 * @Date: 2023/12/6 19:16
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ListNamespaceEventsRequest extends AbstractBceRequest {
    private String userId;
    private String namespace;
    private String name;
    private EventLevelEnum eventLevel;
    private Integer pageNo;
    private Integer pageSize;

    @Override
    public ListNamespaceEventsRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
