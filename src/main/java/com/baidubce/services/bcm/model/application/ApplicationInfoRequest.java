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
public class ApplicationInfoRequest extends AbstractBceRequest {

    private String name;

    private String description;

    private String type;

    private String alias;

    private String userId;



    @Override
    public ApplicationInfoRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
