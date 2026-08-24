package com.baidubce.services.bes.v3.model.snapshot;

import com.baidubce.services.bes.v3.model.AbstractBesV3Request;

import lombok.Data;

import java.util.List;

@Data
public class BatchUpdateAutoSnapshotConfigStatusRequest extends AbstractBesV3Request {
    private String clusterId;
    private Boolean enabled;
    private List<Long> configIdList;
}
