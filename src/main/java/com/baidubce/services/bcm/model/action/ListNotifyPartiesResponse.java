package com.baidubce.services.bcm.model.action;

import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.bcm.model.Page;
import lombok.Data;

@Data
public class ListNotifyPartiesResponse extends AbstractBceResponse {
    private Boolean success;
    private Integer status;
    private Page<NotifyParty> page;
}