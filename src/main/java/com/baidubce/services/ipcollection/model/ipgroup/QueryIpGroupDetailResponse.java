package com.baidubce.services.ipcollection.model.ipgroup;

import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.ipcollection.model.IpCollectionBindedInstance;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
public class QueryIpGroupDetailResponse extends AbstractBceResponse {
    private String ipGroupId;
    private String name;
    private String description;
    private String ipVersion;
    private List<String> ipSetIds;
    private List<IpCollectionBindedInstance> bindedInstances;
}
