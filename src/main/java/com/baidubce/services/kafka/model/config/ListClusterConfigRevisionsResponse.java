package com.baidubce.services.kafka.model.config;

import com.baidubce.model.AbstractBceResponse;
import lombok.Data;

import java.util.List;

@Data
public class ListClusterConfigRevisionsResponse extends AbstractBceResponse {
    private List<ClusterConfigRevision> revisions;
}
