package com.baidubce.services.kafka.model.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClusterConfigRevisionDetail {
    private Integer revisionId;
    private String state;
    private String description;
    private String createTime;
    private List<ClusterConfigOption> context;
}
