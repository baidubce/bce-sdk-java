package com.baidubce.services.bcm.model.custom;

import lombok.Data;

import java.util.List;

/**
 * 有指标和事件的自定义空间对象
 *
 * @Author: wanglu51
 * @Date: 2023/12/6 19:55
 */
@Data
public class NamespaceWithMetricAndEvent {
    private String name;
    private String namespaceAlias;
    private String userId;
    private String comment;
    private List<NamespaceItemView> metrics;
    private List<NamespaceItemView> eventConfigs;

    @Data
    public static class NamespaceItemView {
        private String name;
        private String alias;
    }
}
