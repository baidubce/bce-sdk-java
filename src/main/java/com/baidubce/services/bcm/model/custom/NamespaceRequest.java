package com.baidubce.services.bcm.model.custom;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 自定义空间请求
 *
 * @Author: wanglu51
 * @Date: 2023/12/6 19:16
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NamespaceRequest extends AbstractBceRequest {
    private String userId;
    private String name;
    private String namespaceAlias;
    private String comment;

    @Override
    public NamespaceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
