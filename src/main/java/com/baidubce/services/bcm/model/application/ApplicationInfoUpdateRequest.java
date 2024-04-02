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
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationInfoUpdateRequest extends AbstractBceRequest {

    @NonNull
    private Long id;

    @NonNull
    private String name;

    private String description;

    @NonNull
    private String type;

    private String alias;

    @NonNull
    private String userId;
    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
