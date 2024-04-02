package com.baidubce.services.bcm.model.site;

import com.baidubce.model.AbstractBceResponse;
import lombok.Data;

@Data
public class TaskResponse extends AbstractBceResponse {

    private String taskId;
    private String jobId;

}
