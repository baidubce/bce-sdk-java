package com.baidubce.services.bes.v3.model.diagnosis;

import com.baidubce.services.bes.v3.model.AbstractBesV3Request;

import lombok.Data;

@Data
public class GetDiagnosisBusyStatusRequest extends AbstractBesV3Request {
    private String clusterId;
}
