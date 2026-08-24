package com.baidubce.services.bes.v3.model.diagnosis;

import com.baidubce.model.AbstractBceResponse;

import lombok.Data;

import java.util.List;

@Data
public class ListDiagnosisItemsResponse extends AbstractBceResponse {
    private List<DiagnosisItem> items;
}
