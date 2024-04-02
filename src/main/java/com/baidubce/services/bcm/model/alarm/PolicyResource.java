package com.baidubce.services.bcm.model.alarm;

import com.baidubce.services.bcm.model.Dimension;
import lombok.Data;

import java.util.List;

@Data
public class PolicyResource {
    private List<Dimension> identifiers;
    private List<Dimension> metricDimensions;
}
