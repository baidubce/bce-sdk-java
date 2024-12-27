package com.baidubce.services.ipcollection.model.ipgroup;

import com.baidubce.model.AbstractBceResponse;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * query ip group list response
 */
@Setter
@Getter
@ToString
public class QueryIpGroupListResponse extends AbstractBceResponse {
    private List<IpGroup> ipGroups;
    private String marker;
    private boolean isTruncated;
    private String nextMarker;
    private int maxKeys;
}
