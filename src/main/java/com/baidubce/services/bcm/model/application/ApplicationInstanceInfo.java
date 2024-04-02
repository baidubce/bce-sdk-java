package com.baidubce.services.bcm.model.application;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author gongjiaming
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationInstanceInfo extends AbstractBceRequest {
    private String id;
    private String instanceId;
    private String instanceUuid;
    private String name;
    private String internalIp;
    private String floatingIp;
    private String publicIp;
    private Boolean hasBinded;
    @Override
    public ApplicationInstanceInfo withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
