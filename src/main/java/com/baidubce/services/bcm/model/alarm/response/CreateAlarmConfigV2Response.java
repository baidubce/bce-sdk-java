package com.baidubce.services.bcm.model.alarm.response;

import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.bcm.model.alarm.AlarmConfigV2;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateAlarmConfigV2Response extends AbstractBceResponse {
    private boolean success;
    private String msg;
    private AlarmConfigV2 result;
}
