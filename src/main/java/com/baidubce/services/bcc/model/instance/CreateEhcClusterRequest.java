package com.baidubce.services.bcc.model.instance;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import lombok.Data;

@Data
public class CreateEhcClusterRequest extends AbstractBceRequest {
    /**
     * The name of the ehcCluster.
     */
    private String name;
    /**
     * the name of available zone
     */
    private String zoneName;
    /**
     * The description of the instance.
     */
    private String description;

    public CreateEhcClusterRequest withName(String name) {
        this.name = name;
        return this;
    }

    public CreateEhcClusterRequest withZoneName(String zoneName) {
        this.zoneName = zoneName;
        return this;
    }

    public CreateEhcClusterRequest withDescription(String description) {
        this.description = description;
        return this;
    }

    @Override
    public CreateEhcClusterRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
