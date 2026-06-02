package com.baidubce.services.eiptp.model;

import com.baidubce.model.AbstractBceResponse;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * The response for getting eipTp price.
 */
@Getter
@Setter
@ToString
public class GetEipTpPriceResponse extends AbstractBceResponse {
    /**
     * The price of the eiptp.
     */
    private String price;
}