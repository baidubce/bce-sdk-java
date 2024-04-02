package com.baidubce.services.bcm.model.dashboard;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DashboardNamespace {
    private String namespaceType;
    private String transfer = "";
    private String filter;
    private String name;
    private String instanceName;
    private String region;
    private String bcmService;
    private List<SubService> subService;
}
