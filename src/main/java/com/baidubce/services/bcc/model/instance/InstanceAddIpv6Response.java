package com.baidubce.services.bcc.model.instance;

import com.baidubce.model.AbstractBceResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * @Author: lilu24
 * @Date: 2024-06-12
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class InstanceAddIpv6Response extends AbstractBceResponse {

    private String ipv6Address;
}
