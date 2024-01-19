package com.baidubce.services.kafka.model.cluster;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StorageDynamicRetention {
    private Integer diskUsedThresholdPercent;
    private Integer stepForwardPercent;
    private Long logMinRetentionMs;
}
