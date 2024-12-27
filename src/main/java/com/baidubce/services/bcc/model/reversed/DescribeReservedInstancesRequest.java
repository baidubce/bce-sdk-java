package com.baidubce.services.bcc.model.reversed;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: lilu24
 * @Date: 2024-06-20
 */

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DescribeReservedInstancesRequest extends AbstractBceRequest {

    private List<String> reservedInstanceIds;

    private String reservedInstanceName;

    private String zoneName;

    private String reservedInstanceStatus;

    private String spec;

    private String offeringType;

    private String osType;

    private String instanceId;

    private String instanceName;

    private Boolean isDeduct;

    private String ehcClusterId;

    /**
     * 排序字段
     */
    private String sortKey;

    private String sortDir;

    private String reservedType;


    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
