package com.baidubce.services.bes.v3.model.audit;

import com.baidubce.model.AbstractBceResponse;

import lombok.Data;

import java.util.List;

@Data
public class AuditEventTypesResponse extends AbstractBceResponse {
    private List<String> eventTypes;
}
