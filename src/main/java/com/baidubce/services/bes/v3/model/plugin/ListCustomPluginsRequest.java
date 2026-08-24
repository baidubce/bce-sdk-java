package com.baidubce.services.bes.v3.model.plugin;

import com.baidubce.services.bes.v3.model.AbstractBesV3Request;

import lombok.Data;

@Data
public class ListCustomPluginsRequest extends AbstractBesV3Request {
    private String clusterId;
}
