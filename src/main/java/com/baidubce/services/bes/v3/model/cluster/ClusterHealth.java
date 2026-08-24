package com.baidubce.services.bes.v3.model.cluster;

import lombok.Data;

@Data
public class ClusterHealth {
    private String status;
    private String message;
    private String totalDiskSize;
    private String usedDiskSize;

}
