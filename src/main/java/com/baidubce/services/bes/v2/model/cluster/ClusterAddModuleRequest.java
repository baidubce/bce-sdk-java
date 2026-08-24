package com.baidubce.services.bes.v2.model.cluster;

import com.baidubce.services.bes.v2.model.AbstractBesV2Request;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * Request to add a new module type to a cluster.
 */
@Data
public class ClusterAddModuleRequest extends AbstractBesV2Request {
    private String clusterId;
    private List<ModuleInfo> modules;
    private String resizeMode;
    private String paymentType;

    @Data
    public static class ModuleInfo {
        private String type;
        private String version;
        private String desireInstanceNum;
        private String slotType;
        private DiskSlotInfo diskSlotInfo;
    }

    @Data
    public static class DiskSlotInfo {
        private BigDecimal size;
        private String type;
    }
}
