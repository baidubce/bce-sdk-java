package com.baidubce.services.bes.v2.model.config;

import com.baidubce.services.bes.v2.model.AbstractBesV2Request;
import lombok.Data;

import java.io.File;

/**
 * Request to upload a synonym dict file.
 */
@Data
public class SynonymDictUploadRequest extends AbstractBesV2Request {
    private String clusterId;
    private File file;
}
