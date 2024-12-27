package com.baidubce.services.vod.v2.model;

public class MediaWorkflowRegulationNodeTaskInfo {

    private String nodeId;
    private String nodeName;
    private MediaRegulationTaskInfo regulationTaskInfo;

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public MediaRegulationTaskInfo getRegulationTaskInfo() {
        return regulationTaskInfo;
    }

    public void setRegulationTaskInfo(MediaRegulationTaskInfo regulationTaskInfo) {
        this.regulationTaskInfo = regulationTaskInfo;
    }

}
