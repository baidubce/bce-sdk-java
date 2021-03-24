/*
 * Copyright (C) 2021 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.endpoint.model;

import java.util.ArrayList;
import java.util.List;

import com.baidubce.model.AbstractBceResponse;

/**
 * Created by XingChunyang
 * Date: 2021/01/21.
 */

public class ServiceResponse extends AbstractBceResponse {
    /**
     * The service list
     */
    private List<String> services = new ArrayList<String>();

    public List<String> getServices() {
        return services;
    }

    public void setServices(List<String> services) {
        this.services = services;
    }
}
