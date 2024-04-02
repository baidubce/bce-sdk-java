package com.baidubce.services.bcm.model.site;

import com.baidubce.model.AbstractBceResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * create by pangyangyang on 2023/12/14
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResultResponse<T> extends AbstractBceResponse {

    private String orderBy = "";
    private String order = "";
    private int pageNo = 1;
    private int pageSize = 0;
    private int totalCount = 0;
    private List<T> result;
}
