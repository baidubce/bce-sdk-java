package com.baidubce.services.bcm.model.group;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import lombok.Data;

/**
 * Created by dongjiawei on 2023/12/12.
 */
@Data
public class IGInstanceQuery extends AbstractBceRequest {
    private String userId;
    private Long id;
    private String uuid;
    private String serviceName;
    private String typeName;
    private String region;
    private ViewType viewType;
    private Integer pageNo;
    private Integer pageSize;
    private String keywordType;
    private String keyword;

    @Override
    public IGInstanceQuery withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}