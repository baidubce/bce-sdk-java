package com.baidubce.services.kafka.model.cluster;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClusterDetail {

    private String clusterId;

    private String clusterSid;

    private String name;

    private String region;

    private String type;

    private String mode;

    private String state;

    private Provisioned provisioned;

    private List<Tag> tags;

    private String createTime;
}
