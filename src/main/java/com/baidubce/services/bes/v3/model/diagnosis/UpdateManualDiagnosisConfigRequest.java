package com.baidubce.services.bes.v3.model.diagnosis;

import com.baidubce.services.bes.v3.model.AbstractBesV3Request;

import lombok.Data;

import java.util.List;

@Data
public class UpdateManualDiagnosisConfigRequest extends AbstractBesV3Request {
    private String clusterId;
    private String indices;
    private List<String> items;
}
