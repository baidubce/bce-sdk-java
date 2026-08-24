package com.baidubce.services.bes.v3.model.plugin;

import com.baidubce.services.bes.v3.model.AbstractBesV3Request;

import lombok.Data;

import java.util.List;

@Data
public class UpdateSystemPluginsRequest extends AbstractBesV3Request {
    private String clusterId;
    private List<PluginItem> install;
    private List<PluginItem> uninstall;
}
