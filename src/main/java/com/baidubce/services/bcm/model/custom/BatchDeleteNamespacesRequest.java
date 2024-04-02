package com.baidubce.services.bcm.model.custom;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 批量删除自定义空间请求
 *
 * @Author: wanglu51
 * @Date: 2023/12/6 19:16
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BatchDeleteNamespacesRequest extends AbstractBceRequest {
    private String userId;
    private List<String> names;

    @Override
    public BatchDeleteNamespacesRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
