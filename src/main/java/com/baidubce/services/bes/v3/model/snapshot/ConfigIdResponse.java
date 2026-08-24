package com.baidubce.services.bes.v3.model.snapshot;

import com.baidubce.model.AbstractBceResponse;

import lombok.Data;

@Data
public class ConfigIdResponse extends AbstractBceResponse {
    private Long configId;
}
