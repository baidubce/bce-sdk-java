package com.baidubce.services.bcm.model.site;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * create by pangyangyang on 2021/02/22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskSummaryRequest extends AbstractBceRequest {

    private String userId;
    private String query;
    private String type;
    private int pageNo;
    private int pageSize;

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
