package com.baidubce.services.bcc.model.instance;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import lombok.Data;

@Data
public class ModifyEhcClusterRequest extends AbstractBceRequest {
    /**
     * The id of ehcCluster.
     */
    private String ehcClusterId;
    /**
     * The new value for ehcCluster's name.
     */
    private String name;
    /**
     * The new value for ehcCluster's description.
     */
    private String description;

    public ModifyEhcClusterRequest withEhcClusterId(String ehcClusterId) {
        this.ehcClusterId = ehcClusterId;
        return this;
    }

    public ModifyEhcClusterRequest withName(String name) {
        this.name = name;
        return this;
    }

    public ModifyEhcClusterRequest withDescription(String description) {
        this.description = description;
        return this;
    }

    @Override
    public ModifyEhcClusterRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
