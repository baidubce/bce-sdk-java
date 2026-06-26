package com.baidubce.services.scs.model.group;

import com.baidubce.model.AbstractBceResponse;

import java.util.List;

public class ScsGroupWhiteListResponse extends AbstractBceResponse {
    private List<String> whiteLists;

    public List<String> getWhiteLists() {
        return whiteLists;
    }

    public void setWhiteLists(List<String> whiteLists) {
        this.whiteLists = whiteLists;
    }
}
