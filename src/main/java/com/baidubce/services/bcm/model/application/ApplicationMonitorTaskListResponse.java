package com.baidubce.services.bcm.model.application;

import com.baidubce.model.AbstractBceResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author gongjiaming
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationMonitorTaskListResponse extends AbstractBceResponse {
    private List<ApplicationMonitorTaskResponse> applicationMonitorTaskList;
}
