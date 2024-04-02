package com.baidubce.services.bcm.model.application;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ACMonitorObject {
    private Long id;
    private Set<ACMonitorObjectViewModel> monitorObjectView;
    private MonitorObjectType monitorObjectType;
    private String typeName;
}