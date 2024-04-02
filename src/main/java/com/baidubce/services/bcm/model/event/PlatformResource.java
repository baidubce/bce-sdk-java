package com.baidubce.services.bcm.model.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * Created by dongjiawei on 2023/12/12.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlatformResource {
    private String service;
    private String userId;
    private String instanceId;
    private String shortInstanceId;
    private String instanceName;
}
