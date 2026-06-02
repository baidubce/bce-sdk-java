package com.baidubce.services.bec.model.appblbv2.backendbind;

import com.baidubce.model.AbstractBceResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
/**
 * @Author wangjiang07
 * @Since 2025-08-04 10:10
 * @Version v1.0
 * the response for updating app blb server group port
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdateBecAppBlbServerGroupPortResponse extends AbstractBceResponse {
}
