package com.baidubce.services.bes.v2.model.cluster;

import com.baidubce.services.bes.v2.model.AbstractBesV2Request;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * Request to resize a cluster.
 */
@Data
public class ClusterResizeRequest extends AbstractBesV2Request {
    private String clusterId;
    private List<ModuleInfo> modules;
    private String paymentType;
    private String coupon;
    private String resizeMode;
    private Boolean isShrink;

    @Data
    public class ModuleInfo {
        private String type;
        private String version;
        private Integer desireInstanceNum;
        private String slotType;
        private DiskSlotInfo diskSlotInfo;
    }

    @Data
    public class DiskSlotInfo {
        private BigDecimal size;
        private String type;
        private BigDecimal cdsExtraIo;
    }
}
