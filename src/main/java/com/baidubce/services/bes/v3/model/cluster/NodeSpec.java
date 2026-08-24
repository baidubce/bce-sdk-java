package com.baidubce.services.bes.v3.model.cluster;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NodeSpec {

    private String type;

    private String nodeType;

    private Integer nodeCount;

    private String diskType;

    private Integer diskSize;

    private Integer diskCount;

}
