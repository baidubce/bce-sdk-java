package com.baidubce.services.kafka.model.cluster;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedHashMap;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConfigMeta {

    private String configId;

    private String revisionId;

    private Map<String, String> context = new LinkedHashMap<String, String>();
}
