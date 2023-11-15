package com.baidubce.services.kafka.model;


import com.baidubce.model.AbstractBceRequest;
import lombok.Data;

@Data
public abstract class PageListRequest extends AbstractBceRequest {

    private int pageNo = 1;

    private int pageSize = 10 ;
}
