package com.baidubce.services.bcc.model.reversed;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.baidubce.services.bcc.model.TagModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author: lilu24
 * @Date: 2024-06-20
 */

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateReservedInstancesRequest extends AbstractBceRequest {


    private String reservedInstanceName;

    private String scope;

    private String zoneName;

    private String spec;

    private String offeringType;

    private int instanceCount;

    private int reservedInstanceCount;

    private int reservedInstanceTime;

    private String reservedInstanceTimeUnit;

    private String autoRenewTimeUnit;

    private int autoRenewTime;

    private boolean autoRenew;

    private LocalDateTime effectiveTime;

    private List<TagModel> tags;

    private String ehcClusterId;

    private String ticketId;

    @Override
    public CreateReservedInstancesRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}

