package com.baidubce.services.ipcollection.model.ipset;

import com.baidubce.model.AbstractBceResponse;
import lombok.Getter;
import lombok.Setter;

/**
 * createIpAddressGroup response.
 */
@Getter
@Setter
public class CreateIpAddressSetResponse extends AbstractBceResponse {

    private String ipSetId;

    /**
     * toString
     * @return string
     */
    @Override
    public String toString() {
        return "CreateIpAddressGroupResponse{" +
                "ipSetId='" + ipSetId + '\'' +
                '}';
    }
}
