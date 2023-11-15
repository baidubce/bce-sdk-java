package com.baidubce.services.kafka.model.acl;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import lombok.Data;

import java.util.List;

@Data
public class CreateAclRequest extends AbstractBceRequest {

    /**
     * The id of cluster.
     */
    private String clusterId;

    private String username;

    private String patternType;

    private String resourceType;

    private String resourceName;

    private List<String> operations;

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return public CreateAclRequest withRequestCredentials(BceCredentials credentials) { with credentials.
     */
    @Override
    public CreateAclRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
