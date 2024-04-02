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
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationDimensionTableInfoRequest extends AbstractBceRequest {
    @NotNull
    private String userId;
    @NotNull
    private String appName;
    @NotNull
    private String mapContentJson;
    @NotNull
    private String tableName;
    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
