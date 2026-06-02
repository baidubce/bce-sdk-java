package com.baidubce.services.bcc.model.volume;

import com.baidubce.model.AbstractBceResponse;
import lombok.Data;

import java.util.List;

@Data
public class GetDiskQuotaResponse extends AbstractBceResponse {

    private String cdsTotalCapacityGB;

    private String cdsUsedCapacityGB;

    private List<DiskInfo> diskInfos;

    @Data
    public static class DiskInfo {

        private String storageType;

        private int maxDiskSize;

        private int minDiskSize;
    }
}
