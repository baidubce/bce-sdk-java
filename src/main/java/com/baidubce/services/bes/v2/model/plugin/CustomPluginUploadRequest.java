package com.baidubce.services.bes.v2.model.plugin;

import com.baidubce.services.bes.v2.model.AbstractBesV2Request;
import lombok.Data;

import java.io.File;

/**
 * Request to upload a custom plugin.
 */
@Data
public class CustomPluginUploadRequest extends AbstractBesV2Request {
    private String clusterId;
    private String moduleType;
    private File file;
}
