package com.baidubce.services.bes.v3.model.schedule;

import com.baidubce.model.AbstractBceResponse;

import lombok.Data;

import java.util.List;

@Data
public class ListSchedulesResponse extends AbstractBceResponse {
    private List<ScheduleItem> schedules;
    private Integer pageNo;
    private Integer pageSize;
    private Integer totalCount;
}
