package com.baidubce.services.bcc.model.instance;

import com.baidubce.model.AbstractBceResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BatchChangeToPrepaidResponse extends AbstractBceResponse {
    private String orderId;
}
