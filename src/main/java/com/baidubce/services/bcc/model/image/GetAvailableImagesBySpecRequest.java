package com.baidubce.services.bcc.model.image;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import lombok.Data;

/**
 * @Author: lilu24
 * @Date: 2023-09-05
 */

@Data
public class GetAvailableImagesBySpecRequest extends AbstractBceRequest {

    private String marker = "-1";


    private Integer maxKeys = 10;

    private String spec;

    private String osName;


    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
