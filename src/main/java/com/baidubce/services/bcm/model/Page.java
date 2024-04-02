package com.baidubce.services.bcm.model;


import com.baidubce.model.AbstractBceResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Page<T> extends AbstractBceResponse {
    private String orderBy;

    private String order;

    private Integer pageNo;

    private Integer pageSize;

    private Integer totalCount;

    private List<T> result;
}