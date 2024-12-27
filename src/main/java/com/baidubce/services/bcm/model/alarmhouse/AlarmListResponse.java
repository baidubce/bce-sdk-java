package com.baidubce.services.bcm.model.alarmhouse;

import com.baidubce.model.AbstractBceResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlarmListResponse extends AbstractBceResponse {
    private Boolean success;
    private String msg;
    private PageResult result;
}
