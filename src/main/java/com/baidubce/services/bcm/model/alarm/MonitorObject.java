package com.baidubce.services.bcm.model.alarm;

import com.baidubce.services.bcm.model.application.MonitorObjectType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MonitorObject {
    private MonitorObjectType type;
    private List<String> names;
    private List<PolicyResource> resources;
    private String typeName;
}
