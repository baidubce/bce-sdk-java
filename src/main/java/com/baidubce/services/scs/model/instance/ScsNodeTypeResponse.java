package com.baidubce.services.scs.model.instance;

import com.baidubce.model.AbstractBceResponse;

import java.util.List;

/**
 * Response of scs node type list.
 */
public class ScsNodeTypeResponse extends AbstractBceResponse {

    private List<ScsNodeType> clusterNodeTypeList;
    private List<ScsNodeType> defaultNodeTypeList;
    private List<ScsNodeType> hsdbNodeTypeList;
    private List<ScsNodeType> pegaClusterNodeTypeList;

    public List<ScsNodeType> getClusterNodeTypeList() {
        return clusterNodeTypeList;
    }

    public void setClusterNodeTypeList(List<ScsNodeType> clusterNodeTypeList) {
        this.clusterNodeTypeList = clusterNodeTypeList;
    }

    public List<ScsNodeType> getDefaultNodeTypeList() {
        return defaultNodeTypeList;
    }

    public void setDefaultNodeTypeList(List<ScsNodeType> defaultNodeTypeList) {
        this.defaultNodeTypeList = defaultNodeTypeList;
    }

    public List<ScsNodeType> getHsdbNodeTypeList() {
        return hsdbNodeTypeList;
    }

    public void setHsdbNodeTypeList(List<ScsNodeType> hsdbNodeTypeList) {
        this.hsdbNodeTypeList = hsdbNodeTypeList;
    }

    public List<ScsNodeType> getPegaClusterNodeTypeList() {
        return pegaClusterNodeTypeList;
    }

    public void setPegaClusterNodeTypeList(List<ScsNodeType> pegaClusterNodeTypeList) {
        this.pegaClusterNodeTypeList = pegaClusterNodeTypeList;
    }
}
