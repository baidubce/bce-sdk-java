package com.baidubce.services.ipcollection.model.ipgroup;

import com.baidubce.model.AbstractBceResponse;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * create ip address group response.
 */
@Getter
@Setter
@ToString
public class CreateIpAddressGroupResponse extends AbstractBceResponse {
    private String ipSetId;
}
