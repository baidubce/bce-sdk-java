package com.baidubce.services.bcc.model.instance;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import lombok.Data;

import java.util.List;

@Data
public class DeleteEhcClusterRequest extends AbstractBceRequest {
    /**
     * List of id of ehcCluster.
     */
    private List<String> ehcClusterIdList;

    public DeleteEhcClusterRequest withEhcClusterIdList(List<String> ehcClusterIdList) {
        this.ehcClusterIdList = ehcClusterIdList;
        return this;
    }

    @Override
    public DeleteEhcClusterRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
