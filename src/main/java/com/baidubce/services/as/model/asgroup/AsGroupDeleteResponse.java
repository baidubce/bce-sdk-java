package com.baidubce.services.as.model.asgroup;

import com.baidubce.model.AbstractBceResponse;
import lombok.Data;

import java.util.List;

@Data
public class AsGroupDeleteResponse extends AbstractBceResponse {
    private List<String> groupIds;
}
