package com.baidubce.services.bec.model.resource;

import lombok.Data;

/**
 * Storage partition.
 */
@Data
public class StoragePartition {

    private Integer swapInGb;

    private Integer rootInGb;

    private Integer homeInGb;
}
