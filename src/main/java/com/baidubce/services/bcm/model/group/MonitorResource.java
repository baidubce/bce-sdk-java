package com.baidubce.services.bcm.model.group;

import com.baidubce.services.bcm.model.Dimension;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dongjiawei on 2023/12/12.
 */
@Data
public class MonitorResource {
    private String userId;
    private String region;
    private String serviceName;
    private String typeName;
    private String resourceId;
    private String errUpdateTime;
    private List<Dimension> identifiers = new ArrayList();
    private List<Dimension> properties = new ArrayList();
    private List<Dimension> tags = new ArrayList();
}
