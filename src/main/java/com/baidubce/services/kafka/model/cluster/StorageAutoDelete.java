package com.baidubce.services.kafka.model.cluster;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StorageAutoDelete {
    private Integer diskUsedThresholdPercent;
    private Long logMinRetentionMs;
    private Long logMinRetentionBytes;
}
