package com.baidubce.services.aihc.model.inference;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

import lombok.Setter;
import lombok.Getter;

@Getter
@Setter
public class ListAppRequest extends AbstractBceRequest {

    private int pageNo;
    private int pageSize;
    private String keyword;
    private String order;
    private String orderBy;

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return ListAppRequest with credentials.
     */
    public ListAppRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
