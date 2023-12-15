package com.baidubce.services.eiptp.model;

import com.baidubce.model.AbstractBceResponse;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * The response for creating an eipTp.
 */
@Getter
@Setter
@ToString
public class CreateEipTpResponse extends AbstractBceResponse {
    /**
     * The created eiptp's id.
     * */
    private String id;
}
