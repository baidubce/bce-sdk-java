package com.baidubce.services.kafka.model.acl;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ListAclRequest extends AbstractBceRequest {

    /**
     * The id of cluster.
     */
    @JsonIgnore
    private String clusterId;

    @JsonIgnore
    private String username;

    @JsonIgnore
    private String patternType;

    @JsonIgnore
    private String resourceType;

    @JsonIgnore
    private String resourceName;

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return public ListAclRequest withRequestCredentials(BceCredentials credentials) { with credentials.
     */
    @Override
    public ListAclRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
