package com.baidubce.services.as.model.asgroup;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.LinkedList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TemplateUpdateRequest extends AbstractBceRequest {
    @JsonIgnore
    private String clientToken;
    private NodeInfo node;
    private List<CdsInfo> cds;

    private EipInfo eip = new EipInfo();

    public NodeInfo getNode() {
        return node;
    }

    public void setNode(NodeInfo node) {
        this.node = node;
    }

    public List<CdsInfo> getCds() {
        return cds;
    }

    public void setCds(List<CdsInfo> cds) {
        this.cds = cds;
    }

    public EipInfo getEip() {
        return eip;
    }

    public void setEip(EipInfo eip) {
        this.eip = eip;
    }
    public String getClientToken() {
        return clientToken;
    }

    public void setClientToken(String clientToken) {
        this.clientToken = clientToken;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("TemplateUpdateRequest{");
        sb.append(" node=").append(node);
        sb.append(", cds=").append(cds);
        sb.append(", eip=").append(eip);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
