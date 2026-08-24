package com.baidubce.services.bes.v2.model.plugin;

import com.baidubce.services.bes.v2.model.AbstractBesV2Request;
import lombok.Data;

/**
 * Request to install a custom plugin.
 */
@Data
public class CustomPluginInstallRequest extends AbstractBesV2Request {
    private String clusterId;
    private String pluginName;
    private String moduleType;
}
