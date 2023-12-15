package com.baidubce.services.etgateway.model;

import com.baidubce.model.AbstractBceResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class CreateEtGatewayResponse extends AbstractBceResponse {

    /**
     * The ID of the created dedicated line gateway.
     */
    private String etGatewayId;

}
