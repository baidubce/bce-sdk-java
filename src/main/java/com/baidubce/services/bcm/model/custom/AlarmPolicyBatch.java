package com.baidubce.services.bcm.model.custom;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AlarmPolicyBatch {
    private String userId;
    private String scope;
    private List<String> alarmName;
}
