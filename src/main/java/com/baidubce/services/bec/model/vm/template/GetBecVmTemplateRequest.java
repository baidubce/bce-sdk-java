package com.baidubce.services.bec.model.vm.template;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import lombok.Data;

/**
 * The request for getting the details of the BEC virtual machine template.
 */
@Data
public class GetBecVmTemplateRequest extends AbstractBceRequest {

    /**
     * The id of the virtual machine template.
     */
    private String templateId;

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return GetBecVmTemplateRequest with credentials.
     */
    public GetBecVmTemplateRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
