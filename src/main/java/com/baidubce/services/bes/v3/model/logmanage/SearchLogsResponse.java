package com.baidubce.services.bes.v3.model.logmanage;

import com.baidubce.model.AbstractBceResponse;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class SearchLogsResponse extends AbstractBceResponse {
    private Long total;
    private Integer page;
    private Integer pageSize;
    private List<Map<String, Object>> logs;
}
