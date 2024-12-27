package com.baidubce.services.aihc.model.inference;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListChangeRequest extends AbstractBceRequest {

    private String appId;
    private int changeType;
    private String order;
    private String orderBy;
    private int pageNo;
    private int pageSize;

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return ListChangeRequest with credentials.
     */
    public ListChangeRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
