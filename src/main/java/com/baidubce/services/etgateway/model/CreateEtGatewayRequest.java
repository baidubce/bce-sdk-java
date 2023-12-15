package com.baidubce.services.etgateway.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
public class CreateEtGatewayRequest extends AbstractBceRequest {
    /**
     * An ASCII string whose length is less than 64.
     * <p>
     * The request will be idempotent if clientToken is provided.
     * If the clientToken is not specified by the user, a random String generated by default algorithm will be used.
     * See more detail at
     * <a href = "https://bce.baidu.com/doc/BCC/API.html#.E5.B9.82.E7.AD.89.E6.80.A7">
     * BCE API doc</a>
     */
    @JsonIgnore
    private String clientToken;

    /**
     * The name of the dedicated line gateway, composed of uppercase and lowercase letters, numbers,
     * and special characters -_ /. Must start with a letter and have a length of 1-65 characters
     */
    private String name;

    /**
     * vpcId
     */
    private String vpcId;

    /**
     * The speed limit value of the dedicated line gateway bandwidth, measured in Mbps.
     * The limit is an integer between 2 and 10000.
     */
    private Integer speed;

    /**
     * The description of the dedicated line gateway, not exceeding 200 characters
     */
    private String description;

    /**
     * The ID of the bound physical dedicated line. Both etId and channelId must exist simultaneously.
     */
    private String etId;

    /**
     * The ID of the bound dedicated line channel. Both etId and channelId must exist simultaneously.
     */
    private String channelId;

    /**
     * The cloud-side network of the dedicated line gateway.
     * Users can choose the local VPC subnet or define one or more custom subnets.
     * This setting is only applicable when parameters etId and channelId exist
     */
    private List<String> localCidrs;

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
