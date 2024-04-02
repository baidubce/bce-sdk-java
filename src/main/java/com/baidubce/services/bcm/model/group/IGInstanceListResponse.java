package com.baidubce.services.bcm.model.group;

import com.baidubce.model.AbstractBceResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dongjiawei on 2023/12/12.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class IGInstanceListResponse extends AbstractBceResponse {
    private String orderBy = "";
    private String order = "";
    private int pageNo = 1;
    private int pageSize = 0;
    private int totalCount = 0;
    private List<List<IGInstanceItem>> result = new ArrayList();
}