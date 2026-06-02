package com.baidubce.services.bcc.model.task;

import com.baidubce.model.ListResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;


@Data
@EqualsAndHashCode(callSuper = true)
public class ListTaskByMarkerResponse extends ListResponse {

    private List<BaseTaskUO> tasks;

}
