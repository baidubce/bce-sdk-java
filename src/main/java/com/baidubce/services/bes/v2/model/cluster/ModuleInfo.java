package com.baidubce.services.bes.v2.model.cluster;

import lombok.Data;

/**
 * Node template information for cluster operations.
 */
@Data
public class ModuleInfo {
    private String type;
    private Integer instanceNum;
    private String slotType;
    private DiskSlotInfo diskSlotInfo;
}
