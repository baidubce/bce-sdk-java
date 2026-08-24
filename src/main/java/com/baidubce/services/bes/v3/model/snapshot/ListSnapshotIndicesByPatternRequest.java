package com.baidubce.services.bes.v3.model.snapshot;

import com.baidubce.services.bes.v3.model.AbstractBesV3Request;

import lombok.Data;

@Data
public class ListSnapshotIndicesByPatternRequest extends AbstractBesV3Request {
    private String clusterId;
    private String pattern;
}
