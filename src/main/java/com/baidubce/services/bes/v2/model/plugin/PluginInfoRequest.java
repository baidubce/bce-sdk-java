package com.baidubce.services.bes.v2.model.plugin;

import com.baidubce.services.bes.v2.model.AbstractBesV2Request;
import lombok.Data;

/**
 * Request to get default and custom plugin list.
 */
@Data
public class PluginInfoRequest extends AbstractBesV2Request {
    private String clusterId;
}
