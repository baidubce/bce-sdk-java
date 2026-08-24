package com.baidubce.services.bes.v3.model.snapshot;

import com.baidubce.model.AbstractBceResponse;

import lombok.Data;

@Data
public class RestoreIdResponse extends AbstractBceResponse {
    private Long restoreId;
}
