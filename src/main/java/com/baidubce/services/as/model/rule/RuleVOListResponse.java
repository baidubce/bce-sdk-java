package com.baidubce.services.as.model.rule;

import com.baidubce.model.AbstractBceResponse;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dongjiawei on 2023/12/13.
 */
@Data
public class RuleVOListResponse extends AbstractBceResponse {
    private String orderBy = "";
    // 升序asc 或 降序desc 排序
    private String order = "";
    // 页码
    private int pageNo = 1;
    // 一页条目数量
    private int pageSize = 0;
    // 总条目数量
    private int totalCount = 0;
    // 返回结果
    private List<RuleVO> result = new ArrayList<RuleVO>();
}
