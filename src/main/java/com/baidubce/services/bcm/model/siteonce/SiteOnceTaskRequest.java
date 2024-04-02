package com.baidubce.services.bcm.model.siteonce;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SiteOnceTaskRequest extends AbstractBceRequest {
    private String siteId;
    private String userId;
    private String groupId;
    private int pageNo;
    private int pageSize;
    private String filterArea;
    private String filterIsp;
    private String order;
    private String orderBy;
    private String url;
    private List<String> siteIds;
    @Override
    public SiteOnceTaskRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
