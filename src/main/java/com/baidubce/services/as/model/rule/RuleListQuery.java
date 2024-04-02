package com.baidubce.services.as.model.rule;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import lombok.Data;

/**
 * Created by dongjiawei on 2023/12/13.
 */
@Data
public class RuleListQuery extends AbstractBceRequest {
    /**
     * 伸缩组ID
     * */
    private String groupId;
    /**
     * 过滤规则的关键词
     * */
    private String keyword;
    /**
     * 关键词的类型
     * */
    private String keywordType;
    /**
     * 规则的顺序，默认为"desc"
     * */
    private String order;
    /**
     * 排序规则的字段，默认为"createTime"
     * */
    private String orderBy;
    /**
     * 页码，默认为1
     * */
    private Integer pageNo;
    /**
     * 页面大小，默认为1000
     * */
    private Integer pageSize;

    @Override
    public RuleListQuery withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
