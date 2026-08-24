package com.baidubce.services.bes.v2.model.plugin;

import com.baidubce.services.bes.v2.model.AbstractBesV2Request;
import lombok.Data;

/**
 * Request to uninstall a custom plugin.
 */
@Data
public class CustomPluginUninstallRequest extends AbstractBesV2Request {
    private String clusterId;
    private String pluginName;
    private String moduleType;
}
