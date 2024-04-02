package com.baidubce.services.bcm.model.alarm;

import com.baidubce.services.bcm.model.CommonKV;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class TargetInstance {
    private String region;
    private List<CommonKV> identifiers;
    private List<CommonKV> metricDimensions;
}
