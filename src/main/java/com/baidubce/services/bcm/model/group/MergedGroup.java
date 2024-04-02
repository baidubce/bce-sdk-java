package com.baidubce.services.bcm.model.group;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import lombok.Data;

import java.util.List;

/**
 * Created by dongjiawei on 2023/12/12.
 */
@Data
public class MergedGroup extends AbstractBceRequest {
    private Long id;

    private String userId;

    private String region;

    private String serviceName;

    private String typeName;

    private String name;

    private List<MonitorResource> resourceIdList;

    @Override
    public MergedGroup withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
