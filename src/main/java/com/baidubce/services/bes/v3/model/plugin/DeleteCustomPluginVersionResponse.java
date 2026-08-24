package com.baidubce.services.bes.v3.model.plugin;

import com.baidubce.model.AbstractBceResponse;

import lombok.Data;

@Data
public class DeleteCustomPluginVersionResponse extends AbstractBceResponse {
    private String pluginName;
    private String version;
}
