package com.baidubce.services.ipcollection.model.ipset;

import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.ipcollection.model.IpCollectionBindedInstance;
import com.baidubce.services.ipcollection.model.TemplateIpAddressInfo;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Query IpSet Detail Response
 */
@Getter
@Setter
public class QueryIpSetDetailResponse extends AbstractBceResponse {
    private String ipSetId;
    private String name;
    private String description;
    private String ipVersion;
    private List<TemplateIpAddressInfo> ipAddressInfo;
    private List<IpCollectionBindedInstance> bindedInstances;

    /**
     * toString
     * @return string
     */
    @Override
    public String toString() {
        return "QueryIpSetDetailResponse{" +
                "ipSetId='" + ipSetId + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", ipVersion='" + ipVersion + '\'' +
                ", ipAddressInfo=" + ipAddressInfo +
                ", bindedInstances=" + bindedInstances +
                '}';
    }
}
