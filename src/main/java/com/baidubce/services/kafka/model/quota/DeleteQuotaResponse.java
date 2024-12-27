package com.baidubce.services.kafka.model.quota;

import com.baidubce.model.AbstractBceResponse;

import lombok.Data;

@Data
public class DeleteQuotaResponse extends AbstractBceResponse {

    private String username;

    private Boolean userDefault;

    private String clientId;

    private Boolean clientDefault;
}
