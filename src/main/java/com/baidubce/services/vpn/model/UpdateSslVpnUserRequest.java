package com.baidubce.services.vpn.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class UpdateSslVpnUserRequest extends AbstractBceRequest {
    /**
     * An ASCII string whose length is less than 64.
     * The request will be idempotent if client token is provided.
     * If the clientToken is not specified by the user, a random String generated by default algorithm will be used.
     * See more detail at
     * <a href = "https://bce.baidu.com/doc/BCC/API.html#.E5.B9.82.E7.AD.89.E6.80.A7">
     * BCE API doc</a>
     */
    @JsonIgnore
    private String clientToken;

    private String vpnId;
    private String userId;
    private String password;
    private String description;



    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return DeleteEniRequest with credentials.
     */
    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
