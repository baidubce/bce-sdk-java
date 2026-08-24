package com.baidubce.services.bes.v2.model.plugin;

import com.baidubce.services.bes.v2.model.AbstractBesV2Request;
import lombok.Data;

/**
 * Request to delete a custom plugin.
 */
@Data
public class CustomPluginDeleteRequest extends AbstractBesV2Request {
    private String clusterId;
    private String pluginName;
    private String moduleType;
}
