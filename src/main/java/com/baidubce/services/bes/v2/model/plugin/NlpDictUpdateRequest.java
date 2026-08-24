package com.baidubce.services.bes.v2.model.plugin;

import com.baidubce.services.bes.v2.model.AbstractBesV2Request;
import lombok.Data;

import java.io.File;

/**
 * Request to upload the nlp dict.
 */
@Data
public class NlpDictUpdateRequest extends AbstractBesV2Request {
    private String clusterId;
    private String separator;
    private File file;
}
