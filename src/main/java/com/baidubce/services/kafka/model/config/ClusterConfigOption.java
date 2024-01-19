package com.baidubce.services.kafka.model.config;

import com.baidubce.services.kafka.model.cluster.ClusterConfigOverrideMode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClusterConfigOption {
    private String name;
    private String description;
    private String updateMode;
    private Object[] scope;
    private Object defaultValue;
    private Object currentValue;
    private String type;
    private String unit;
    private String category;
    private ClusterConfigOverrideMode overrideMode;
}
