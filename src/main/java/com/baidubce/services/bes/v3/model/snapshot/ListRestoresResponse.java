package com.baidubce.services.bes.v3.model.snapshot;

import com.baidubce.model.AbstractBceResponse;

import lombok.Data;

import java.util.List;

@Data
public class ListRestoresResponse extends AbstractBceResponse {
    private List<RestoreItem> restores;
    private Integer pageNo;
    private Integer pageSize;
    private Integer totalCount;
    private String orderBy;
    private String order;
}
