package com.baidubce.services.bcm.model.alarm;

import com.baidubce.services.bcm.model.CommonKV;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class AlarmConfigPolicyRuleV2 {
    private String metricName;
    private List<CommonKV> metricDimensions;
    private String operator;
    private String statistics;
    private double threshold;
    private int window = 60;
}
