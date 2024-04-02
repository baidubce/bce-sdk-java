package com.baidubce.services.bcm.model.site;

import com.baidubce.model.AbstractBceResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * create by pangyangyang on 2021/02/22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskSummaryResponse extends AbstractBceResponse {

    private String taskId;
    private String taskName;
    private int cycle;
    private String type;
    private String address;
    private String status;
}
