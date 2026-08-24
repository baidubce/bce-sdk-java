package com.baidubce.services.bes.v2.model.renew;

import com.baidubce.model.AbstractBceResponse;
import lombok.Data;

import java.util.List;

/**
 * Response for listing renewals.
 */
@Data
public class RenewListResponse extends AbstractBceResponse {
    private Boolean success;
    private Integer status;
    private Page page;

    @Data
    public static class Page {
        private String orderBy;
        private String order;
        private Integer pageNo;
        private Integer pageSize;
        private Integer totalCount;
        private List<RenewalCluster> result;
    }

    @Data
    public static class RenewalCluster {
        private String clusterId;
        private String clusterName;
        private String region;
        private String expiredTime;
        private String clusterStatus;
    }
}
