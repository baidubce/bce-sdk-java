package com.baidubce.services.bcc.model.instance;

import com.baidubce.model.AbstractBceResponse;
import lombok.Data;

@Data
public class CreateEhcClusterResponse extends AbstractBceResponse {
    /**
     * The id of ehcCluster.
     */
    private String ehcClusterId;
}
