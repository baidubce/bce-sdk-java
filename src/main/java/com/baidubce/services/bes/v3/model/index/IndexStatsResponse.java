package com.baidubce.services.bes.v3.model.index;

import com.baidubce.model.AbstractBceResponse;
import lombok.Data;

import java.util.Map;

@Data
public class IndexStatsResponse extends AbstractBceResponse {
    private Map<String, Object> stats;
}
