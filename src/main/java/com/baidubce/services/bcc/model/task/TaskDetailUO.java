package com.baidubce.services.bcc.model.task;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class TaskDetailUO extends BaseTaskUO {

    private List<OperationProgressUO> operationProgressSet;

}
