package com.baidubce.services.bcm.model.application;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * @author gongjiaming
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationDataListRequest extends AbstractBceRequest {
    @NonNull
    private Integer pageNo;
    @NonNull
    private Integer pageSize;

    private String searchName;
    @Override
    public ApplicationDataListRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
