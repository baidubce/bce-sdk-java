package com.baidubce.services.bcm.model.custom;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

/**
 * @author guanyanyan
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MetricDimensions {

    private String name;

    @Builder.Default
    private Set<String> value = new HashSet();
}
