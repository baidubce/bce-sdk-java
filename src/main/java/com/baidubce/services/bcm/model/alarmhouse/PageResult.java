package com.baidubce.services.bcm.model.alarmhouse;

import lombok.Data;

import java.util.List;

@Data
public class PageResult {
    private List<Alarm> alarms;
    private Integer pageNo;
    private Integer pageSize;
    private Integer totalCount;
}
