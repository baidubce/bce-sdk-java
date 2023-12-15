/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.eip.model;

import lombok.Getter;
import lombok.Setter;

/**
 * The request for listing recycle eip.
 */
@Getter
@Setter
public class ListRecycleEipsRequest extends ListRequest {
    /**
     * eip address condition
     */
    private String eip;
    /**
     * eip name condition
     */
    private String name;

    public ListRecycleEipsRequest withEip(String eip) {
        this.eip = eip;
        return this;
    }

    public ListRecycleEipsRequest withName(String name) {
        this.name = name;
        return this;
    }
}
