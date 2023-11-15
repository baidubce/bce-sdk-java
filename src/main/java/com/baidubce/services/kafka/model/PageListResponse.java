package com.baidubce.services.kafka.model;

import com.baidubce.model.AbstractBceResponse;
import lombok.Data;

@Data
public class PageListResponse extends AbstractBceResponse {

    private int pageNo;

    private int pageSize;

    private int total;
}
