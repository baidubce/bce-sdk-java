package com.baidubce.services.bec.model.appblbv2.backendbind;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
/**
 * @Author wangjiang07
 * @Since 2025-08-04 10:10
 * @Version v1.0
 * the res for server group port
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServerGroupPortRes {
    private String healthCheck;
    private Integer healthCheckDownRetry;
    private String healthCheckHost;
    private Integer healthCheckIntervalInSecond;
    private String healthCheckNormalStatus;
    private Integer healthCheckPort;
    private Integer healthCheckTimeoutInSecond;
    private Integer healthCheckUpRetry;
    private String healthCheckUrlPath;
    private String id;
    private Integer port;
    private String type;
    private String udpHealthCheckString;
}
