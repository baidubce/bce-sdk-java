package com.baidubce.services.bcm.model.application;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @author gongjiaming
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationInstanceDeleteRequest extends AbstractBceRequest {
    @NotNull
    private String id;

    @NotNull
    private String appName;
    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
