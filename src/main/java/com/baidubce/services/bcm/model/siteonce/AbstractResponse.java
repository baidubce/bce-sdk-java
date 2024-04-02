package com.baidubce.services.bcm.model.siteonce;

import com.baidubce.model.AbstractBceResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author guanyanyan
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AbstractResponse extends AbstractBceResponse {

    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private String requestId;

    private String message = "";

    private Boolean success = true;

}
