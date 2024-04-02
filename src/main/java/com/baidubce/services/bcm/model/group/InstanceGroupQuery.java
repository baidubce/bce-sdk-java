package com.baidubce.services.bcm.model.group;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import lombok.Data;

/**
 * Created by dongjiawei on 2023/12/12.
 */
@Data
public class InstanceGroupQuery extends AbstractBceRequest {

    private String userId;
    private String name;
    private String serviceName;
    private String region;
    private String typeName;
    private Integer pageNo;
    private Integer pageSize;

    @Override
    public InstanceGroupQuery withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}