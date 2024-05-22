package com.baidubce.services.bcc.model.volume;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class VolumePriceRequest  extends AbstractBceRequest {

    @JsonIgnore
    private String clientToken;

    private Integer purchaseLength;
    private String paymentTiming;
    private String storageType;
    private Integer cdsSizeInGB;
    private Integer purchaseCount;
    private String zoneName;


    @Override
    public VolumePriceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
