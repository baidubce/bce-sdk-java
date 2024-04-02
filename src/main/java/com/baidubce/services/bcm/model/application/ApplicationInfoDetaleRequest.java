package com.baidubce.services.bcm.model.application;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationInfoDetaleRequest extends AbstractBceRequest {
    @NonNull
    private String name;
    @Override
    public ApplicationInfoDetaleRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
