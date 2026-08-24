package com.baidubce.services.bes.v3.model.index;

import com.baidubce.model.AbstractBceResponse;
import lombok.Data;

@Data
public class IndexExistResponse extends AbstractBceResponse {
    private Boolean exist;
}
