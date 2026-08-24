package com.baidubce.services.bes.v3.model.billing;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 字段与 {@link com.baidubce.services.bes.v3.model.cluster.NodeSpec} 一致；
 * 避免跨包依赖在此重复定义，两者变更时需同步。
 */
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
