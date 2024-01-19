package com.baidubce.services.kafka.model.config;

import com.baidubce.model.ListResponse;
import lombok.Data;

import java.util.List;

@Data
public class ListClusterConfigsResponse extends ListResponse {

    private List<ClusterConfig> configs;
}
