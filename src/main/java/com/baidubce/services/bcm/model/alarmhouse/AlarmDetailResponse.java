package com.baidubce.services.bcm.model.alarmhouse;

import com.baidubce.model.AbstractBceResponse;
import lombok.Data;

@Data
public class AlarmDetailResponse extends AbstractBceResponse {
    private Boolean success;
    private String msg;
    private Alarm result;
}
