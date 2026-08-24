package com.baidubce.services.bes.v3.model.index;

import com.baidubce.services.bes.v3.model.AbstractBesV3Request;
import lombok.Data;

@Data
public class GetIndexFieldTypesRequest extends AbstractBesV3Request {
    private String clusterId;
}
