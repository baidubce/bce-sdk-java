package com.baidubce.services.bcm.model.group;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import lombok.Data;


/**
 * Created by dongjiawei on 2023/12/12.
 */
@Data
public class InstanceGroup extends AbstractBceRequest {

    private Long id;

    private String name;

    private String serviceName;

    private String typeName;

    private String region;

    private String userId;

    private String uuid;

    private int count;

    private String serviceNameAlias;

    private String typeNameAlias;

    private String regionAlias;

    private String tagKey = "";

    @Override
    public InstanceGroup withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
