package com.baidubce.services.bmr.model;

public class SchedulePlanModelVo extends SchedulePlanSummaryModelVo {
    private ClusterTemplateInfoVo clusterTemplate;

    public ClusterTemplateInfoVo getClusterTemplate() {
        return clusterTemplate;
    }

    public void setClusterTemplate(ClusterTemplateInfoVo clusterTemplate) {
        this.clusterTemplate = clusterTemplate;
    }
}
