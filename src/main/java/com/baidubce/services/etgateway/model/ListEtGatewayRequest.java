package com.baidubce.services.etgateway.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.baidubce.model.ListRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class ListEtGatewayRequest extends ListRequest {

    /**
     * vpcId is required
     */
    private String vpcId;

    /**
     * optional
     */
    private String name;

    /**
     * optional
     */
    private String etGatewayId;

    /**
     * optional
     */
    private String status;


    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
