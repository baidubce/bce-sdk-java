/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.eipbp.model;

import com.baidubce.model.AbstractBceResponse;
import lombok.ToString;

/**
 * The response for create eipBp.
 */
@ToString
public class CreateEipBpResponse extends AbstractBceResponse {

    /**
     * The created eipBp's id.
     * */
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
