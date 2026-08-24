package com.baidubce.services.bes.v3.model.cluster;

import com.baidubce.services.bes.v3.model.AbstractBesV3Request;

import lombok.Data;

import java.util.List;

@Data
public class MigrateDataRequest extends AbstractBesV3Request {
    private String clusterId;
    private List<String> nodes;
    private String type;
}
