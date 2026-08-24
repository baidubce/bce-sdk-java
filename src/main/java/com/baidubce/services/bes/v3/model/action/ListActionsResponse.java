package com.baidubce.services.bes.v3.model.action;

import com.baidubce.model.AbstractBceResponse;

import lombok.Data;

import java.util.List;

@Data
public class ListActionsResponse extends AbstractBceResponse {
    private List<ActionItem> result;
    private Integer pageNo;
    private Integer pageSize;
    private Integer totalCount;
}
