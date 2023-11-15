package com.baidubce.services.kafka.model.cluster;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StorageMeta {

    private StorageType storageType;

    private int storageSize;

    private int numberOfDisk = 1;
}
