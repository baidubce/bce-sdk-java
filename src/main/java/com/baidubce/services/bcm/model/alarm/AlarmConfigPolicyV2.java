package com.baidubce.services.bcm.model.alarm;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class AlarmConfigPolicyV2 {
    private List<AlarmConfigPolicyRuleV2> rules;
    private int alarmPendingPeriodCount;
}
