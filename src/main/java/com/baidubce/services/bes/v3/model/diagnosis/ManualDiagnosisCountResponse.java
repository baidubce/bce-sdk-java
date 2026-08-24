package com.baidubce.services.bes.v3.model.diagnosis;

import com.baidubce.model.AbstractBceResponse;

import lombok.Data;

@Data
public class ManualDiagnosisCountResponse extends AbstractBceResponse {
    private Integer count;
}
