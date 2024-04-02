package com.baidubce.services.bcm.model.application;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Metric {
    private long id;
    private long taskId;
    private String metricName;
    private String metricAlias;
    private String metricUnit;
    private int valueFieldType;
    private String valueFieldName;
    private String valueMatchRule;

    private List<AggrTag> aggrTags;

    private int saveInstanceData;
}
