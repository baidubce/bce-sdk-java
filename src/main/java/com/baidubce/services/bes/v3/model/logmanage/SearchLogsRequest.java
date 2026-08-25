package com.baidubce.services.bes.v3.model.logmanage;

import com.baidubce.services.bes.v3.model.AbstractBesV3Request;

import lombok.Data;

@Data
public class SearchLogsRequest extends AbstractBesV3Request {
    private String clusterId;
    private String logType;
    private String keyword;
    private String level;
    private String nodeName;
    private String startTime;
    private String endTime;
    private Integer page;
    private Integer pageSize;
}
