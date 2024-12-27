package com.baidubce.services.kafka.model.quota;

import com.baidubce.model.AbstractBceResponse;

import lombok.Data;

@Data
public class UpdateQuotaResponse extends AbstractBceResponse {

    private Quota quota;
}
