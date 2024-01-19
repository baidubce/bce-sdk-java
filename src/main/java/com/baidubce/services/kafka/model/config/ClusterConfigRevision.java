package com.baidubce.services.kafka.model.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClusterConfigRevision {
    private Integer revisionId;
    private String state;
    private String description;
    private String createTime;
}
