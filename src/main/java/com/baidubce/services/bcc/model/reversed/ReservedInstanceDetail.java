package com.baidubce.services.bcc.model.reversed;

import com.baidubce.services.bcc.model.TagModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
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
public class ReservedInstanceDetail {

    private String reservedInstanceId;

    private String reservedInstanceUuid;

    private String reservedInstanceName;

    private String scope;

    private String zoneName;

    private String spec;

    private String reservedType;

    private String offeringType;

    private String osType;

    private String reservedInstanceStatus;

    private Integer instanceCount;

    private String instanceId;

    private String instanceName;

    private LocalDateTime effectiveTime;

    private LocalDateTime expireTime;

    private boolean autoRenew;

    private String renewTimeUnit;

    private Integer renewTime;

    private LocalDateTime nextRenewTime;

    private String flavorSubType;

    private List<TagModel> tags;

    @JsonProperty("isNeedEhcCluster")
    private boolean isNeedEhcCluster;
}
