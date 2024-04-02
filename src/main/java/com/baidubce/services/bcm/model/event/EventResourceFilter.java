package com.baidubce.services.bcm.model.event;

import com.baidubce.services.bcm.model.application.MonitorObjectType;
import lombok.Data;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dongjiawei on 2023/12/12.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class EventResourceFilter {
    private String region;

    private String type;

    private MonitorObjectType monitorObjectType = MonitorObjectType.ALL;

    private List<EventResource> resources = new ArrayList<EventResource>();

}
