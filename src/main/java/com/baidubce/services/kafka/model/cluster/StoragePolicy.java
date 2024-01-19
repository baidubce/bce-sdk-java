package com.baidubce.services.kafka.model.cluster;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoragePolicy {
    private StoragePolicyType type;
    private StorageAutoDelete autoDelete;
    private StorageAutoExpand autoExpand;
    private StorageDynamicRetention dynamicRetention;
}