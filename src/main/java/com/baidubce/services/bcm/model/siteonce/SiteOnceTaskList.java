package com.baidubce.services.bcm.model.siteonce;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SiteOnceTaskList {
    private int pageNo;
    private int pageSize;
    private int totalCount;
    private List<SiteOnceRecord> taskList;
}
