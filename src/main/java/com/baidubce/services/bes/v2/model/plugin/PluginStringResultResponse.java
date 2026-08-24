package com.baidubce.services.bes.v2.model.plugin;

import com.baidubce.model.AbstractBceResponse;
import lombok.Data;

/**
 * Common response for BES v2 plugin operations (installDefaultPlugin/uninstallDefaultPlugin/
 * deleteCustomPlugin/uploadCustomPlugin/updateNlpDict) whose result was originally documented
 * as a plain string, but observed in practice to also come back as a structured object
 * (e.g. {"code":"SUCCESS","message":""}) — result is typed as Object to accept either shape
 * without failing deserialization.
 */
@Data
public class PluginStringResultResponse extends AbstractBceResponse {
    private Boolean success;
    private Integer status;
    private Object result;
}
