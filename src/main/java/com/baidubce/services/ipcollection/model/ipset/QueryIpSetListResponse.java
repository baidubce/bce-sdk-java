package com.baidubce.services.ipcollection.model.ipset;

import com.baidubce.model.AbstractBceResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * query ip set list response.
 */
@Getter
@Setter
public class QueryIpSetListResponse extends AbstractBceResponse {
    private List<IpSet> ipSets;
    private String marker;
    private boolean isTruncated;
    private String nextMarker;
    private Integer maxKeys;

    /**
     * toString
     * @return string
     */
    @Override
    public String toString() {
        return "QueryIpSetListResponse{" +
                "ipSets=" + ipSets +
                ", marker='" + marker + '\'' +
                ", isTruncated=" + isTruncated +
                ", nextMarker='" + nextMarker + '\'' +
                ", maxKeys=" + maxKeys +
                '}';
    }
}
