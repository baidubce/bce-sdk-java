package com.baidubce.services.bcc.model.instance;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import lombok.Data;

import java.util.List;

@Data
public class DescribeEhcClusterListRequest extends AbstractBceRequest {
    /**
     * List of id of ehcCluster.
     */
    private List<String> ehcClusterIdList;
    /**
     * List of name of ehcCluster.
     */
    private List<String> nameList;
    /**
     * the name of available zone
     */
    private String zoneName;

    public DescribeEhcClusterListRequest withEhcClusterIdList(List<String> ehcClusterIdList) {
        this.ehcClusterIdList = ehcClusterIdList;
        return this;
    }

    public DescribeEhcClusterListRequest withNameList(List<String> nameList) {
        this.nameList = nameList;
        return this;
    }

    public DescribeEhcClusterListRequest withZoneName(String zoneName) {
        this.zoneName = zoneName;
        return this;
    }

    @Override
    public DescribeEhcClusterListRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
