package com.baidubce.services.bes.v2.model.cluster;

import lombok.Data;

/**
 * Disk configuration for a cluster node.
 */
@Data
public class DiskSlotInfo {
    private Integer size;
    private String type;
    private Integer cdsExtraIo;
}
